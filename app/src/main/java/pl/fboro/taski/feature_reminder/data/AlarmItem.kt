package pl.fboro.taski.feature_reminder.data

import java.time.LocalDateTime

data class AlarmItem(
    val time: LocalDateTime,
    val id: Int,
    val title: String,
    val dueDateDay: Int,
    val dueDateMonth: Int,
    val dueDateHour: Int,
    val dueDateMinute: Int,
) {
}