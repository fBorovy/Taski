package pl.fboro.taski.feature_task.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import pl.fboro.taski.feature_calendar.utils.MyDate
import pl.fboro.taski.feature_calendar.utils.currentDay
import pl.fboro.taski.feature_calendar.utils.currentMonth
import pl.fboro.taski.feature_calendar.utils.currentYear
import pl.fboro.taski.feature_task.data.PresentationMode
import pl.fboro.taski.feature_task.data.TaskDao
import pl.fboro.taski.feature_task.data.TaskEvent
import pl.fboro.taski.feature_task.data.TaskState
import pl.fboro.taski.feature_task.domain.model.Task

@OptIn(ExperimentalCoroutinesApi::class)
class TaskViewModel(
    private val dao: TaskDao
): ViewModel() {

    private val _presentationMode = MutableStateFlow(PresentationMode.SHOW_ALL)
    private val _selectedDueDate = MutableStateFlow(MyDate(currentDay, currentMonth, currentYear, 12, 0))
    private val _tasks = _presentationMode
        .flatMapLatest { presentationMode ->
            when(presentationMode) {
                PresentationMode.SHOW_ALL -> dao.getAllTasks()
                PresentationMode.SHOW_DONE -> dao.getAllDoneTasks()
                PresentationMode.SHOW_UNDONE -> dao.getAllUndoneTasks()
                PresentationMode.SHOW_ALL_SPECIFIED_DATE -> dao.getSpecifiedDayAllActivities(dueDate = _selectedDueDate.value)
                PresentationMode.SHOW_DONE_SPECIFIED_DATE -> dao.getSpecifiedDayDoneTask(dueDate = _selectedDueDate.value)
                PresentationMode.SHOW_UNDONE_SPECIFIED_DATE -> dao.getSpecifiedDayUndoneTask(dueDate = _selectedDueDate.value)
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(TaskState())

    //variable to combine state of three other states and expose it to the UI
    val state = combine(_state, _presentationMode, _selectedDueDate, _tasks) {
            state, presentationMode, selectedDueDate, tasks ->
        state.copy(
            selectedPresentationMode = presentationMode,
            selectedDueDate = selectedDueDate,
            tasks = tasks
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000), TaskState())

    fun onEvent(event: TaskEvent) {
        when(event) {
            is TaskEvent.SetTitle -> {
                _state.update { it.copy(
                    title = event.title
                ) }
            }
            is TaskEvent.SetDescription -> {
                _state.update { it.copy(
                    description = event.description
                ) }
            }
            is TaskEvent.DeleteTask -> {
                viewModelScope.launch {
                    dao.delete(event.task)
                }
            }
            is TaskEvent.SetDueDate -> {
                _state.update { it.copy(
                    dueDate = event.dueDate
                ) }
            }
            is TaskEvent.SetReminder1 -> {
                _state.update { it.copy(
                    reminderDate1 = event.reminder1
                ) }
            }
            is TaskEvent.SetReminder2 -> {
                _state.update { it.copy(
                    reminderDate2 = event.reminder2
                ) }
            }
            is TaskEvent.SetReminder3 -> {
                _state.update { it.copy(
                    reminderDate3 = event.reminder3
                ) }
            }
            is TaskEvent.ChangePresentationMode -> {
                _state.update { it.copy(
                    selectedPresentationMode = event.mode
                )}
            }
            is TaskEvent.ChangePresentationDate -> {
                _selectedDueDate.value = event.presentationDate
            }
            TaskEvent.SaveTask -> {
                val title = state.value.title
                val description = state.value.description
                val isDone = state.value.isDone
                val dueDate = state.value.dueDate
                val reminder1: MyDate? = state.value.reminderDate1
                val reminder2: MyDate? = state.value.reminderDate2
                val reminder3: MyDate? = state.value.reminderDate3

                if (title.isBlank() || description.isBlank()) {
                    return
                }

                val task = Task(
                    title = title,
                    description = description,
                    isDone = isDone,
                    dueDate = state.value.dueDate,
                    reminderDate1 = reminder1,
                    reminderDate2 = reminder2,
                    reminderDate3 = reminder3,
                )
                viewModelScope.launch {
                    dao.upsertTask(task)
                }

                _state.update { it.copy(
                    title = "",
                    description = "",
                    isDone = false,
                    dueDate = MyDate(1,1,2023, 12, 0),
                    reminderDate1 = null,
                    reminderDate2 = null,
                    reminderDate3 = null,
                ) }
            }
        }
    }
}