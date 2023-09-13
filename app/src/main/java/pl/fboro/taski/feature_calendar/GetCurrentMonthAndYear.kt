package pl.fboro.taski.feature_calendar

import java.time.LocalDate

fun getCurrentMonthAndYear(): Pair<Int, Int> {
    val currentDate = LocalDate.now()
    val year = currentDate.year
    val month = currentDate.monthValue - 1
    return Pair(month, year)
}