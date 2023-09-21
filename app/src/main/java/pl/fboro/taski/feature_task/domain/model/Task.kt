package pl.fboro.taski.feature_task.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.fboro.taski.feature_calendar.utils.MyDate

@Entity
class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val isDone: Boolean,
    val dueDate: MyDate,
    val reminderDate1: MyDate?,
    val reminderDate2: MyDate?,
    val reminderDate3: MyDate?,
)