package pl.fboro.taski.feature_calendar.utils

fun getDaysOfMonthAmount(month: Int, year: Int): Int {

    if (year % 4 == 0 && month == 2) return 29
    if (month == 2) return 28
    when(month % 2) {
        0 -> return 31
        1 -> return 30
    }
    return 31
}