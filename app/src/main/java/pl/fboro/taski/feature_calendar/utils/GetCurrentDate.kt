package pl.fboro.taski.feature_calendar.utils

import java.time.LocalDate

fun getCurrentDate(): List<Int> {
    val currentDate = LocalDate.now()
    val year = currentDate.year
    val month = currentDate.monthValue - 1
    val day = currentDate.dayOfMonth
    return listOf(day, month, year)
}