package pl.fboro.taski.feature_calendar

import androidx.compose.runtime.Composable
import java.time.LocalDate

@Composable
fun getStartingDay(month: Int, year: Int): Int {

    val firstDayOfMonth = LocalDate.of(year, month + 1, 1)

    return firstDayOfMonth.dayOfWeek.value - 1
}

//LocalDate numerates from 1, so I had to add + 1 to month and substrate 1 from day of week