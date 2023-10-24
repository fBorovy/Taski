package pl.fboro.taski.feature_task.data

import pl.fboro.taski.feature_calendar.utils.MyDate
import pl.fboro.taski.feature_task.domain.model.Task

data class TaskState(
    val tasks: List<Task> = emptyList(),
    val title: String = "",
    val description: String = "",
    val isDone: Boolean = false,
    val dueDate: MyDate = MyDate(1,1,2023, 12, 0),
    val reminderDate1: MyDate? = null,
    val reminderDate2: MyDate? = null,
    val reminderDate3: MyDate? = null,
    val selectedPresentationMode: PresentationMode = PresentationMode.SHOW_ALL,
    val selectedDueDate: MyDate? = null,
)
