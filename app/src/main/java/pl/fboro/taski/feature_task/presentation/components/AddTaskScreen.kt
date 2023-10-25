package pl.fboro.taski.feature_task.presentation.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chargemap.compose.numberpicker.NumberPicker
import pl.fboro.taski.R
import pl.fboro.taski.feature_calendar.utils.currentDay
import pl.fboro.taski.feature_calendar.utils.currentMonth
import pl.fboro.taski.feature_calendar.utils.currentYear
import pl.fboro.taski.feature_calendar.utils.getDaysOfMonthAmount
import pl.fboro.taski.ui.theme.AddTaskScreenLabelColor
import pl.fboro.taski.ui.theme.BackgroundColor
import pl.fboro.taski.ui.theme.Typography


@Composable
fun AddTaskScreen(navController: NavController) {
    val context: Context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(
                colors = listOf(Color.White, BackgroundColor),
                startY = -100f
            ))
            .padding(top = 20.dp)
            .padding(horizontal = 15.dp)
    ) {

        var taskTitle by remember{ mutableStateOf("") }
        var taskDescription by remember{ mutableStateOf("") }

        var dueDateDay by remember { mutableStateOf(currentDay) }
        var dueDateMonth by remember { mutableStateOf(currentMonth) }
        var dueDateYear by remember { mutableStateOf(currentYear) }
        var dueHour by remember { mutableStateOf(8) }
        var dueMinute by remember { mutableStateOf( 0) }

        var showReminderDate1 by remember { mutableStateOf(false) }
        var showReminderDate2 by remember { mutableStateOf(false) }
        var showReminderDate3 by remember { mutableStateOf(false) }

        var daysAmount = getDaysOfMonthAmount(dueDateMonth, dueDateYear)



        TransparentTextField(
            value = taskTitle,
            placeholder = context.resources.getString(R.string.title_placeholder),
            singleLine = true,
            imeAction = ImeAction.Next,
        ){
            taskTitle = it
        }
        TransparentTextField(
            value = taskDescription,
            placeholder = context.resources.getString(R.string.description_placeholder),
            singleLine = false,
            imeAction = ImeAction.Next,
        ){
            taskDescription = it
        }

        Text(
            modifier = Modifier.padding(start = 15.dp, top = 10.dp),
            text = context.resources.getString(R.string.deadline),
            style = Typography.body2
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row {
                NumberPicker(
                    value = dueDateDay,
                    range = 1..daysAmount,
                    textStyle = Typography.body2,

                    dividersColor = AddTaskScreenLabelColor,
                    onValueChange = {
                        dueDateDay = it
                    }
                )

                Spacer(modifier = Modifier.width(1.dp))

                NumberPicker(
                    value = dueDateMonth,
                    range = 1..12,
                    textStyle = Typography.body2,

                    dividersColor = AddTaskScreenLabelColor,
                    onValueChange = {
                        dueDateMonth = it
                        daysAmount = getDaysOfMonthAmount(dueDateMonth, dueDateYear)
                    }
                )

                Spacer(modifier = Modifier.width(1.dp))

                NumberPicker(
                    value = dueDateYear,
                    range = currentYear..currentYear + 1,
                    textStyle = Typography.body2,

                    dividersColor = AddTaskScreenLabelColor,
                    onValueChange = {
                        dueDateYear = it
                        daysAmount = getDaysOfMonthAmount(dueDateMonth, dueDateYear)
                    }
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically){
                NumberPicker(
                    value = dueHour,
                    range = 0..23,
                    textStyle = Typography.body2,

                    dividersColor = AddTaskScreenLabelColor,
                    onValueChange = {
                        dueHour = it
                    }
                )

                Text(
                    text = ":",
                    style = Typography.body2,
                    fontSize = 20.sp
                )

                NumberPicker(
                    value = dueMinute,
                    range = 1..59,
                    textStyle = Typography.body2,

                    dividersColor = AddTaskScreenLabelColor,
                    onValueChange = {
                        dueMinute = it
                    }
                )
            }
        }
        Text(
            modifier = Modifier
                .padding(start = 15.dp, top = 10.dp)
                .clickable{
                          showReminderDate1 = !showReminderDate1
                },
            text = context.resources.getString(R.string.addReminder),
            style = Typography.body2
        )
        if (showReminderDate1) {
            var reminder1Day by remember { mutableStateOf(currentDay) }
            var reminder1Month by remember { mutableStateOf(currentMonth) }
            var reminder1Year by remember { mutableStateOf(currentYear) }
            var reminder1Hour by remember { mutableStateOf(8) }
            var reminder1Minute by remember { mutableStateOf( 0) }
            var reminder1DaysAmount = getDaysOfMonthAmount(reminder1Month, reminder1Year)

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row {
                    NumberPicker(
                        value = reminder1Day,
                        range = 1..reminder1DaysAmount,
                        textStyle = Typography.body2,

                        dividersColor = AddTaskScreenLabelColor,
                        onValueChange = {
                            reminder1Day = it
                        }
                    )

                    Spacer(modifier = Modifier.width(1.dp))

                    NumberPicker(
                        value = reminder1Month,
                        range = 1..12,
                        textStyle = Typography.body2,

                        dividersColor = AddTaskScreenLabelColor,
                        onValueChange = {
                            reminder1Month = it
                            reminder1DaysAmount = getDaysOfMonthAmount(reminder1Month, reminder1Year)
                        }
                    )

                    Spacer(modifier = Modifier.width(1.dp))

                    NumberPicker(
                        value = reminder1Year,
                        range = currentYear..currentYear + 1,
                        textStyle = Typography.body2,

                        dividersColor = AddTaskScreenLabelColor,
                        onValueChange = {
                            reminder1Year = it
                            reminder1DaysAmount = getDaysOfMonthAmount(reminder1Month, reminder1Year)
                        }
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically){
                    NumberPicker(
                        value = reminder1Hour,
                        range = 0..23,
                        textStyle = Typography.body2,

                        dividersColor = AddTaskScreenLabelColor,
                        onValueChange = {
                            reminder1Hour = it
                        }
                    )

                    Text(
                        text = ":",
                        style = Typography.body2,
                        fontSize = 20.sp
                    )

                    NumberPicker(
                        value = reminder1Minute,
                        range = 1..59,
                        textStyle = Typography.body2,

                        dividersColor = AddTaskScreenLabelColor,
                        onValueChange = {
                            reminder1Minute = it
                        }
                    )
                }
            }
            Text(
                modifier = Modifier
                    .padding(start = 15.dp, top = 10.dp)
                    .clickable{
                        showReminderDate2 = !showReminderDate2
                    },
                text = context.resources.getString(R.string.addReminder),
                style = Typography.body2
            )
            if (showReminderDate2) {
                var reminder2Day by remember { mutableStateOf(currentDay) }
                var reminder2Month by remember { mutableStateOf(currentMonth) }
                var reminder2Year by remember { mutableStateOf(currentYear) }
                var reminder2Hour by remember { mutableStateOf(8) }
                var reminder2Minute by remember { mutableStateOf( 0) }
                var reminder2DaysAmount = getDaysOfMonthAmount(reminder2Month, reminder2Year)

                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Row {
                        NumberPicker(
                            value = reminder2Day,
                            range = 1..reminder2DaysAmount,
                            textStyle = Typography.body2,

                            dividersColor = AddTaskScreenLabelColor,
                            onValueChange = {
                                reminder2Day = it
                            }
                        )

                        Spacer(modifier = Modifier.width(1.dp))

                        NumberPicker(
                            value = reminder2Month,
                            range = 1..12,
                            textStyle = Typography.body2,

                            dividersColor = AddTaskScreenLabelColor,
                            onValueChange = {
                                reminder2Month = it
                                reminder2DaysAmount = getDaysOfMonthAmount(reminder2Month, reminder2Year)
                            }
                        )

                        Spacer(modifier = Modifier.width(1.dp))

                        NumberPicker(
                            value = reminder2Year,
                            range = currentYear..currentYear + 1,
                            textStyle = Typography.body2,

                            dividersColor = AddTaskScreenLabelColor,
                            onValueChange = {
                                reminder2Year = it
                                reminder2DaysAmount = getDaysOfMonthAmount(reminder2Month, reminder2Year)
                            }
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically){
                        NumberPicker(
                            value = reminder2Hour,
                            range = 0..23,
                            textStyle = Typography.body2,

                            dividersColor = AddTaskScreenLabelColor,
                            onValueChange = {
                                reminder2Hour = it
                            }
                        )

                        Text(
                            text = ":",
                            style = Typography.body2,
                            fontSize = 20.sp
                        )

                        NumberPicker(
                            value = reminder2Minute,
                            range = 1..59,
                            textStyle = Typography.body2,

                            dividersColor = AddTaskScreenLabelColor,
                            onValueChange = {
                                reminder2Minute = it
                            }
                        )
                    }
                }
                Text(
                    modifier = Modifier
                        .padding(start = 15.dp, top = 10.dp)
                        .clickable{
                            showReminderDate3 = !showReminderDate3
                        },
                    text = context.resources.getString(R.string.addReminder),
                    style = Typography.body2
                )
                if (showReminderDate3) {
                    var reminder3Day by remember { mutableStateOf(currentDay) }
                    var reminder3Month by remember { mutableStateOf(currentMonth) }
                    var reminder3Year by remember { mutableStateOf(currentYear) }
                    var reminder3Hour by remember { mutableStateOf(8) }
                    var reminder3Minute by remember { mutableStateOf( 0) }
                    var reminder3DaysAmount = getDaysOfMonthAmount(reminder3Month, reminder3Year)

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row {
                            NumberPicker(
                                value = reminder3Day,
                                range = 1..reminder3DaysAmount,
                                textStyle = Typography.body2,

                                dividersColor = AddTaskScreenLabelColor,
                                onValueChange = {
                                    reminder3Day = it
                                }
                            )

                            Spacer(modifier = Modifier.width(1.dp))

                            NumberPicker(
                                value = reminder3Month,
                                range = 1..12,
                                textStyle = Typography.body2,

                                dividersColor = AddTaskScreenLabelColor,
                                onValueChange = {
                                    reminder3Month = it
                                    reminder3DaysAmount =
                                        getDaysOfMonthAmount(reminder3Month, reminder3Year)
                                }
                            )

                            Spacer(modifier = Modifier.width(1.dp))

                            NumberPicker(
                                value = reminder3Year,
                                range = currentYear..currentYear + 1,
                                textStyle = Typography.body2,

                                dividersColor = AddTaskScreenLabelColor,
                                onValueChange = {
                                    reminder3Year = it
                                    reminder3DaysAmount =
                                        getDaysOfMonthAmount(reminder3Month, reminder3Year)
                                }
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            NumberPicker(
                                value = reminder3Hour,
                                range = 0..23,
                                textStyle = Typography.body2,

                                dividersColor = AddTaskScreenLabelColor,
                                onValueChange = {
                                    reminder3Hour = it
                                }
                            )

                            Text(
                                text = ":",
                                style = Typography.body2,
                                fontSize = 20.sp
                            )

                            NumberPicker(
                                value = reminder3Minute,
                                range = 1..59,
                                textStyle = Typography.body2,

                                dividersColor = AddTaskScreenLabelColor,
                                onValueChange = {
                                    reminder3Minute = it
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}