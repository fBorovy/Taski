package pl.fboro.taski.feature_task.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
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
    private val _selectedDueDate = MutableStateFlow(listOf(0, currentMonth, currentYear))
    private val _tasks = _presentationMode
        .flatMapLatest { presentationMode ->
            when(presentationMode) {
                PresentationMode.SHOW_ALL -> dao.getAllTasks()
                PresentationMode.SHOW_DONE -> dao.getAllDoneTasks()
                PresentationMode.SHOW_UNDONE -> dao.getAllUndoneTasks()
                PresentationMode.SHOW_ALL_SPECIFIED_DATE -> dao.getSpecifiedDayAllTasks(_selectedDueDate.value[0], _selectedDueDate.value[1] + 1, _selectedDueDate.value[2])
                PresentationMode.SHOW_DONE_SPECIFIED_DATE -> dao.getSpecifiedDayDoneTask(_selectedDueDate.value[0], _selectedDueDate.value[1] + 1, _selectedDueDate.value[2])
                PresentationMode.SHOW_UNDONE_SPECIFIED_DATE -> dao.getSpecifiedDayUndoneTask(_selectedDueDate.value[0], _selectedDueDate.value[1] + 1, _selectedDueDate.value[2])
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

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
            is TaskEvent.SetId -> {
                _state.update { it.copy(
                    taskId = event.id
                ) }
            }
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
            is TaskEvent.ChangePresentationMode -> {
                _presentationMode.value = event.mode
            }
            is TaskEvent.ChangePresentationDate -> {
                _selectedDueDate.value = listOf(event.day, event.month, event.year)
            }
            is TaskEvent.SaveTask -> {
                val title = _state.value.title
                val description = _state.value.description
                val isDone = _state.value.isDone
                val dueDateDay = _state.value.dueDateDay
                val dueDateMonth = _state.value.dueDateMonth
                val dueDateYear = _state.value.dueDateYear
                val dueDateHour = _state.value.dueDateHour
                val dueDateMinute = _state.value.dueDateMinute
                val r1Day = _state.value.reminder1Day
                val r1Month = _state.value.reminder1Month
                val r1Year = _state.value.reminder1Year
                val r1Hour = _state.value.reminder1Hour
                val r1Minute = _state.value.reminder1Minute
                val r2Day = _state.value.reminder2Day
                val r2Month = _state.value.reminder2Month
                val r2Year = _state.value.reminder2Year
                val r2Hour = _state.value.reminder2Hour
                val r2Minute = _state.value.reminder2Minute
                val r3Day = _state.value.reminder3Day
                val r3Month = _state.value.reminder3Month
                val r3Year = _state.value.reminder3Year
                val r3Hour = _state.value.reminder3Hour
                val r3Minute = _state.value.reminder3Minute

                if (title.isBlank() || description.isBlank()) {
                    return
                }

                val task = Task(
                    title = title,
                    description = description,
                    isDone = isDone,
                    dueDateDay = dueDateDay,
                    dueDateMonth = dueDateMonth,
                    dueDateYear = dueDateYear,
                    dueDateHour = dueDateHour,
                    dueDateMinute = dueDateMinute,
                    reminder1Day = r1Day,
                    reminder1Month = r1Month,
                    reminder1Year = r1Year,
                    reminder1Hour = r1Hour,
                    reminder1Minute = r1Minute,
                    reminder2Day = r2Day,
                    reminder2Month = r2Month,
                    reminder2Year = r2Year,
                    reminder2Hour = r2Hour,
                    reminder2Minute = r2Minute,
                    reminder3Day = r3Day,
                    reminder3Month = r3Month,
                    reminder3Year = r3Year,
                    reminder3Hour = r3Hour,
                    reminder3Minute = r3Minute,

                    )
                viewModelScope.launch {
                    dao.upsertTask(task)
                }

                _state.update {
                    it.copy(
                        title = "",
                        description = "",
                        isDone = false,
                        dueDateDay = 0,
                        dueDateMonth = 0,
                        dueDateYear = 0,
                        dueDateHour = 0,
                        dueDateMinute = 0,
                        reminder1Day = null,
                        reminder1Month = null,
                        reminder1Year = null,
                        reminder1Hour = null,
                        reminder1Minute = null,
                        reminder2Day = null,
                        reminder2Month = null,
                        reminder2Year = null,
                        reminder2Hour = null,
                        reminder2Minute = null,
                        reminder3Day = null,
                        reminder3Month = null,
                        reminder3Year = null,
                        reminder3Hour = null,
                        reminder3Minute = null,
                    )
                }
            }
            is TaskEvent.SetDueDateDay -> _state.update {
                it.copy( dueDateDay = event.dueDay )
            }
            is TaskEvent.SetDueDateHour -> _state.update {
                it.copy( dueDateHour = event.dueHour )
            }
            is TaskEvent.SetDueDateMinute -> _state.update {
                it.copy( dueDateMinute = event.dueMinute )
            }
            is TaskEvent.SetDueDateMonth -> _state.update {
                it.copy( dueDateMonth = event.dueMonth )
            }
            is TaskEvent.SetDueDateYear -> _state.update {
                it.copy( dueDateYear = event.dueYear )
            }
            is TaskEvent.SetReminder1Day -> _state.update {
                it.copy( reminder1Day = event.r1Day )
            }
            is TaskEvent.SetReminder1Hour -> _state.update {
                it.copy( reminder1Hour = event.r1Hour )
            }
            is TaskEvent.SetReminder1Minute -> _state.update {
                it.copy( reminder1Minute = event.r1Minute )
            }
            is TaskEvent.SetReminder1Month -> _state.update {
                it.copy( reminder1Month = event.r1Month )
            }
            is TaskEvent.SetReminder1Year -> _state.update {
                it.copy( reminder1Year = event.r1Year )
            }
            is TaskEvent.SetReminder2Day -> _state.update {
                it.copy( reminder2Day = event.r2Day )
            }
            is TaskEvent.SetReminder2Hour -> _state.update {
                it.copy( reminder2Hour = event.r2Hour )
            }
            is TaskEvent.SetReminder2Minute -> _state.update {
                it.copy( reminder2Minute = event.r2Minute )
            }
            is TaskEvent.SetReminder2Month -> _state.update {
                it.copy( reminder2Month = event.r2Month )
            }
            is TaskEvent.SetReminder2Year -> _state.update {
                it.copy( reminder2Year = event.r2Year )
            }
            is TaskEvent.SetReminder3Day -> _state.update {
                it.copy( reminder3Day = event.r3Day )
            }
            is TaskEvent.SetReminder3Hour -> _state.update {
                it.copy( reminder3Hour = event.r3Hour )
            }
            is TaskEvent.SetReminder3Minute -> _state.update {
                it.copy( reminder3Minute = event.r3Minute )
            }
            is TaskEvent.SetReminder3Month -> _state.update {
                it.copy( reminder3Month = event.r3Month)
            }
            is TaskEvent.SetReminder3Year -> _state.update {
                it.copy( reminder3Year = event.r3Year )
            }
            is TaskEvent.EditTask -> {
                viewModelScope.launch {
                    val existingTask = dao.getTaskById(event.taskId)
                    existingTask?.let {
                        val updatedTask = it.copy(
                            title = _state.value.title,
                            description = _state.value.description,
                            isDone = _state.value.isDone,
                            dueDateDay = _state.value.dueDateDay,
                            dueDateMonth = _state.value.dueDateMonth,
                            dueDateYear = _state.value.dueDateYear,
                            dueDateHour = _state.value.dueDateHour,
                            dueDateMinute = _state.value.dueDateMinute,
                            reminder1Day = _state.value.reminder1Day,
                            reminder1Month = _state.value.reminder1Month,
                            reminder1Year = _state.value.reminder1Year,
                            reminder1Hour = _state.value.reminder1Hour,
                            reminder1Minute = _state.value.reminder1Minute,
                            reminder2Day = _state.value.reminder2Day,
                            reminder2Month = _state.value.reminder2Month,
                            reminder2Year = _state.value.reminder2Year,
                            reminder2Hour = _state.value.reminder2Hour,
                            reminder2Minute = _state.value.reminder2Minute,
                            reminder3Day = _state.value.reminder3Day,
                            reminder3Month = _state.value.reminder3Month,
                            reminder3Year = _state.value.reminder3Year,
                            reminder3Hour = _state.value.reminder3Hour,
                            reminder3Minute = _state.value.reminder3Minute,
                        )
                        dao.upsertTask(updatedTask)
                    }
                    _state.update {
                        it.copy(
                            title = "",
                            description = "",
                            isDone = false,
                            dueDateDay = 0,
                            dueDateMonth = 0,
                            dueDateYear = 0,
                            dueDateHour = 0,
                            dueDateMinute = 0,
                            reminder1Day = null,
                            reminder1Month = null,
                            reminder1Year = null,
                            reminder1Hour = null,
                            reminder1Minute = null,
                            reminder2Day = null,
                            reminder2Month = null,
                            reminder2Year = null,
                            reminder2Hour = null,
                            reminder2Minute = null,
                            reminder3Day = null,
                            reminder3Month = null,
                            reminder3Year = null,
                            reminder3Hour = null,
                            reminder3Minute = null,
                        )
                    }
                }
            }
            is TaskEvent.SetCompletionStatus -> _state.update {
                it.copy(isDone = event.isDone)
            }
        }
    }
}