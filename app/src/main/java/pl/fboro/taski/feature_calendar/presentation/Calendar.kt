package pl.fboro.taski.feature_calendar.presentation

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pl.fboro.taski.R
import pl.fboro.taski.feature_calendar.utils.getCurrentDate
import pl.fboro.taski.feature_task.data.TaskEvent
import pl.fboro.taski.feature_task.data.TaskState

@Composable
fun Calendar(
    state: TaskState,
    onEvent: (TaskEvent) -> Unit,
) {

    val context: Context = LocalContext.current
    val months: Array<String> = context.resources.getStringArray(R.array.months)
    val currentDate = getCurrentDate()
    var month by remember{ mutableStateOf(currentDate[1]) }
    var year by remember{ mutableStateOf(currentDate[2]) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ){
        Row(
            modifier = Modifier.align(CenterHorizontally)
        ){
            Box(
                modifier = Modifier
                    .align(CenterVertically)
                    .clip(CircleShape)
                    .clickable {
                        year = if (month == 0) year - 1 else year
                        month = if (month == 0) 11 else month - 1
                    }
            ) {
                Image(
                    painterResource(id = R.drawable.ic_baseline_keyboard_arrow_left_30),
                    contentDescription = context.resources.getString(R.string.prev_month)
                )
            }
            Text(
                text = "   " + months[month] + " " + year + "   ",
                style = MaterialTheme.typography.h1
            )
            Box(
                modifier = Modifier
                    .align(CenterVertically)
                    .clip(CircleShape)
                    .clickable {
                        year = if (month == 11) year + 1 else year
                        month = if (month == 11) 0 else month + 1
                    }
            ) {
                Image(
                    painterResource(id = R.drawable.ic_baseline_keyboard_arrow_right_30),
                    contentDescription = context.resources.getString(R.string.next_month)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        CalendarGrid(month = month, year = year, currentDate = currentDate, state, onEvent)
    }
}