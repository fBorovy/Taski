package pl.fboro.taski.feature_calendar

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import pl.fboro.taski.R

@Composable
fun CalendarGrid(month: Int, year: Int) {

    val context: Context = LocalContext.current
    val days: Array<String> = context.resources.getStringArray(R.array.days)
    val boxSize: Dp = 35.dp
    val previousMonth: Int = if (month > 0) month - 1 else 11
    val previousMonthDaysAmount = countMonthDaysAmount(previousMonth, year)
    val monthDaysAmount = countMonthDaysAmount(month, year)
    var startingDay = getStartingDay(month, year) - 1
    var currentMonthDay = 1
    var nextMonthDay = 1

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
                        ) {
                            if (j == 0) {
                                Text(text = days[i])
                            }
                            if (j > 0 && i > 0 && i < 8) {
                                if (startingDay > -1) {
                                    Text(
                                        text = " ${previousMonthDaysAmount - startingDay}",
                                        color = Color.Gray
                                    )
                                    --startingDay
                                }
                                else if (startingDay == -1 && currentMonthDay <= monthDaysAmount) {
                                    Text(text = "$currentMonthDay")
                                    ++currentMonthDay
                                }
                                else {
                                    Text(
                                        text = "$nextMonthDay",
                                        color = Color.Gray,
                                    )
                                    ++nextMonthDay
                                }
                            }

                            DrawLines(j, i)
                        }
                    }
                }
            }
        }
    }
}