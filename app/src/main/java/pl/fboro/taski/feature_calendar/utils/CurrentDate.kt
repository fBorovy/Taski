package pl.fboro.taski.feature_calendar.utils

import java.util.*

val calendar: Calendar = Calendar.getInstance()
val currentYear = calendar.get(Calendar.YEAR)
val currentMonth = calendar.get(Calendar.MONTH) + 1
val currentDay = calendar.get(Calendar.DAY_OF_MONTH)