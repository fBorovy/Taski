package pl.fboro.taski.feature_calendar.presentation

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import pl.fboro.taski.R
import pl.fboro.taski.feature_calendar.DrawLines
import pl.fboro.taski.feature_calendar.countMonthDaysAmount
import pl.fboro.taski.feature_calendar.getStartingDay
import pl.fboro.taski.feature_calendar.utils.MyDate
import pl.fboro.taski.ui.theme.ChosenDayBoxBackgroundColor

@Composable
fun CalendarGrid(
    month: Int,
    year: Int,
    currentDate: MyDate,
    chosenDate: MyDate,
    changeChosenDate: (MyDate) -> Unit,
) {

    val context: Context = LocalContext.current
    val days: Array<String> = context.resources.getStringArray(R.array.days)
    val boxSize: Dp = 35.dp
    val previousMonth: Int = if (month > 0) month - 1 else 11
    val previousMonthDaysAmount = countMonthDaysAmount(previousMonth, year)
    val monthDaysAmount = countMonthDaysAmount(month, year)
    var startingDay = getStartingDay(month, year) - 1
    val sDay = startingDay
    var currentMonthDay = 1
    var nextMonthDay = 1
    var redundantSign = false


    Box(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            for(j in 0..6) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(boxSize)
                ){
                    for (i in 0..8) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .background(
                                    if (currentMonthDay <= monthDaysAmount &&
                                        currentMonthDay == chosenDate.day &&
                                        month == chosenDate.month &&
                                        year == chosenDate.year &&
                                        j > 0 && i in 1..7 &&
                                        startingDay == -1
                                    )
                                        ChosenDayBoxBackgroundColor
                                    else Color.Transparent
                                )
                                .clickable{
                                    if ( j > 0 && i in 1..7)
                                    {
                                        val clickedDay = (j-1) * 7 + (i-1) - sDay
                                        changeChosenDate(MyDate(clickedDay, month, year, null, null))
                                    }
                                }
                        ) {
                            if (j == 0) {
                                Text(
                                    modifier = Modifier.align(Alignment.Center),
                                    text = days[i]
                                )
                            }
                            if (j > 0 && i > 0 && i < 8) {
                                if (startingDay > -1) {
                                    Text(
                                        modifier = Modifier.align(Alignment.Center),
                                        text = " ${previousMonthDaysAmount - startingDay}",
                                        color = Color.Gray
                                    )
                                    --startingDay
                                }
                                else if (startingDay == -1 && currentMonthDay <= monthDaysAmount) {
                                    Text(
                                        modifier = Modifier.align(Alignment.Center),
                                        text = "$currentMonthDay"
                                    )
                                    ++currentMonthDay
                                }
                                else {
                                    Text(
                                        modifier = Modifier.align(Alignment.Center),
                                        text = "$nextMonthDay",
                                        color = Color.Gray,
                                    )
                                    ++nextMonthDay
                                }
                            }

                            if (currentMonthDay == currentDate.day + 1 &&
                                month == currentDate.month &&
                                year == currentDate.year && !redundantSign
                            ) {
                                Image(
                                    modifier = Modifier.align(Alignment.Center),
                                    painter = painterResource(id = R.drawable.circle),
                                    contentDescription = context.resources.getString(R.string.current_date_indicator),
                                )
                                redundantSign = true
                            }
                            DrawLines(j, i)
                        }
                    }
                }
            }
        }
    }
}