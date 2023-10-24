package pl.fboro.taski.feature_task.data

import pl.fboro.taski.feature_calendar.utils.MyDate
import pl.fboro.taski.feature_task.domain.model.Task

sealed interface TaskEvent{

    object SaveTask: TaskEvent
    data class SetTitle(val title: String): TaskEvent
    data class SetDescription(val description: String): TaskEvent
    data class SetDueDate(val dueDate: MyDate): TaskEvent
    data class SetReminder1(val reminder1: MyDate): TaskEvent
    data class SetReminder2(val reminder2: MyDate): TaskEvent
    data class SetReminder3(val reminder3: MyDate): TaskEvent
    data class DeleteTask(val task: Task): TaskEvent
    data class ChangePresentationMode(val mode: PresentationMode): TaskEvent
    data class ChangePresentationDate(val presentationDate: MyDate): TaskEvent

}
