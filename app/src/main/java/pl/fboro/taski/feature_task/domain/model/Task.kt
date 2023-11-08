package pl.fboro.taski.feature_task.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val isDone: Boolean,
    val dueDateDay: Int,
    val dueDateMonth: Int,
    val dueDateYear: Int,
    val dueDateHour: Int,
    val dueDateMinute: Int,
    val reminder1Day: Int?,
    val reminder1Month: Int?,
    val reminder1Year: Int?,
    val reminder1Hour: Int?,
    val reminder1Minute: Int?,
    val reminder2Day: Int?,
    val reminder2Month: Int?,
    val reminder2Year: Int?,
    val reminder2Hour: Int?,
    val reminder2Minute: Int?,
    val reminder3Day: Int?,
    val reminder3Month: Int?,
    val reminder3Year: Int?,
    val reminder3Hour: Int?,
    val reminder3Minute: Int?,
)