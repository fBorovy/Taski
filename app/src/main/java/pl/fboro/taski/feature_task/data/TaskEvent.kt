package pl.fboro.taski.feature_task.data

import pl.fboro.taski.feature_task.domain.model.Task

sealed interface TaskEvent{

    object SaveTask: TaskEvent
    data class SetTitle(val title: String): TaskEvent
    data class SetDescription(val description: String): TaskEvent
    data class SetDueDateDay(val dueDay: Int): TaskEvent
    data class SetDueDateMonth(val dueMonth: Int): TaskEvent
    data class SetDueDateYear(val dueYear: Int): TaskEvent
    data class SetDueDateHour(val dueHour: Int): TaskEvent
    data class SetDueDateMinute(val dueMinute: Int): TaskEvent
    data class SetReminder1Day(val r1Day: Int): TaskEvent
    data class SetReminder1Month(val r1Month: Int): TaskEvent
    data class SetReminder1Year(val r1Year: Int): TaskEvent
    data class SetReminder1Hour(val r1Hour: Int): TaskEvent
    data class SetReminder1Minute(val r1Minute: Int): TaskEvent
    data class SetReminder2Day(val r2Day: Int): TaskEvent
    data class SetReminder2Month(val r2Month: Int): TaskEvent
    data class SetReminder2Year(val r2Year: Int): TaskEvent
    data class SetReminder2Hour(val r2Hour: Int): TaskEvent
    data class SetReminder2Minute(val r2Minute: Int): TaskEvent
    data class SetReminder3Day(val r3Day: Int): TaskEvent
    data class SetReminder3Month(val r3Month: Int): TaskEvent
    data class SetReminder3Year(val r3Year: Int): TaskEvent
    data class SetReminder3Hour(val r3Hour: Int): TaskEvent
    data class SetReminder3Minute(val r3Minute: Int): TaskEvent
    data class DeleteTask(val task: Task): TaskEvent
    data class ChangePresentationMode(val mode: PresentationMode): TaskEvent
    data class ChangePresentationDate(val day: Int, val month: Int, val year: Int): TaskEvent

}
