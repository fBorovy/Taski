package pl.fboro.taski.feature_task.utils

import androidx.room.TypeConverter
import pl.fboro.taski.feature_calendar.utils.MyDate

class MyDateConverter {

    @TypeConverter
    fun myDateToString(myDate: MyDate): String {
        return "${myDate.day}.${myDate.month}.${myDate.year} ${myDate.hour}:${myDate.minute}"
    }

    @TypeConverter
    fun stringToMyDate(myDateString: String): MyDate {
        val parts = myDateString.split(" ")
        val dateParts = parts[0].split(".")
        val timeParts = parts[1].split(":")

        val day = dateParts[0].toInt()
        val month = dateParts[1].toInt()
        val year = dateParts[2].toInt()
        val hour = timeParts[0].toInt()
        val minute = timeParts[1].toInt()

        return MyDate(day, month, year, hour, minute)
    }
}