package pl.fboro.taski.feature_calendar.utils

fun getMonthDaysAmount(month: Int, year: Int): Int {
    when (month) {
        0 -> return 31
        1 -> return if (year%4 == 0) 29 else 28
        2 -> return 31
        3 -> return 30
        4 -> return 31
        5 -> return 30
        6 -> return 31
        7 -> return 31
        8 -> return 30
        9 -> return 31
        10 -> return 30
        11 -> return 31
    }
    return 0
}
