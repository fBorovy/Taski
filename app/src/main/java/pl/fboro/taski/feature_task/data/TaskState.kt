package pl.fboro.taski.feature_task.data

import pl.fboro.taski.feature_task.domain.model.Task

data class TaskState(
    val tasks: List<Task> = emptyList(),
    val title: String = "",
    val description: String = "",
    val isDone: Boolean = false,
    val dueDateDay: Int = 0,
    val dueDateMonth: Int = 0,
    val dueDateYear: Int = 0,
    val dueDateHour: Int = 0,
    val dueDateMinute: Int = 0,
    val reminder1Day: Int? = null,
    val reminder1Month: Int? = null,
    val reminder1Year: Int? = null,
    val reminder1Hour: Int? = null,
    val reminder1Minute: Int? = null,
    val reminder2Day: Int? = null,
    val reminder2Month: Int? = null,
    val reminder2Year: Int? = null,
    val reminder2Hour: Int? = null,
    val reminder2Minute: Int? = null,
    val reminder3Day: Int? = null,
    val reminder3Month: Int? = null,
    val reminder3Year: Int? = null,
    val reminder3Hour: Int? = null,
    val reminder3Minute: Int? = null,
    val selectedPresentationMode: PresentationMode = PresentationMode.SHOW_ALL,
    val selectedDueDate: List<Int> = listOf(0,0,0),
)
