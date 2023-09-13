package pl.fboro.taski.feature_task.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val day: Int,
    val month: Int,
    val year: Int,
    val hour: Int,
    val minute: Int,
    val reminder1: Int,
    val reminder2: Int,
    val reminder3: Int,
    val isDone: Boolean
)