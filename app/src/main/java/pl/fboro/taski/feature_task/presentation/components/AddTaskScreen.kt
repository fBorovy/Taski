package pl.fboro.taski.feature_task.presentation.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chargemap.compose.numberpicker.NumberPicker
import pl.fboro.taski.R
import pl.fboro.taski.feature_calendar.utils.currentDay
import pl.fboro.taski.feature_calendar.utils.currentMonth
import pl.fboro.taski.feature_calendar.utils.currentYear
import pl.fboro.taski.feature_calendar.utils.getMonthDaysAmount
import pl.fboro.taski.feature_reminder.data.AlarmItem
import pl.fboro.taski.feature_reminder.data.AndroidAlarmScheduler
import pl.fboro.taski.feature_task.data.TaskEvent
import pl.fboro.taski.feature_task.utils.editTaskId
import pl.fboro.taski.ui.theme.AddTaskButtonColor
import pl.fboro.taski.ui.theme.AddTaskScreenLabelColor
import pl.fboro.taski.ui.theme.BackgroundColor
import pl.fboro.taski.ui.theme.Typography
import java.time.LocalDateTime


@Composable
fun AddTaskScreen(
    navController: NavController,
    applicationContext: Context,
    onEvent: (TaskEvent) -> Unit,
) {
    val context: Context = LocalContext.current
    val scheduler = AndroidAlarmScheduler(applicationContext)

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
    var daysAmount = getMonthDaysAmount(dueDateMonth - 1, dueDateYear)

    var reminder1Day by remember { mutableStateOf(currentDay) }
    var reminder1Month by remember { mutableStateOf(currentMonth) }
    var reminder1Year by remember { mutableStateOf(currentYear) }
    var reminder1Hour by remember { mutableStateOf(8) }
    var reminder1Minute by remember { mutableStateOf( 0) }

    var reminder2Day by remember { mutableStateOf(currentDay) }
    var reminder2Month by remember { mutableStateOf(currentMonth) }
    var reminder2Year by remember { mutableStateOf(currentYear) }
    var reminder2Hour by remember { mutableStateOf(8) }
    var reminder2Minute by remember { mutableStateOf( 0) }

    var reminder3Day by remember { mutableStateOf(currentDay) }
    var reminder3Month by remember { mutableStateOf(currentMonth) }
    var reminder3Year by remember { mutableStateOf(currentYear) }
    var reminder3Hour by remember { mutableStateOf(8) }
    var reminder3Minute by remember { mutableStateOf(0) }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(
                colors = listOf(Color.White, BackgroundColor),
                startY = -100f
            ))
            .padding(top = 20.dp)
    ){
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(shape = RoundedCornerShape(60.dp, 0.dp, 0.dp, 60.dp))
                .background(AddTaskButtonColor)
                .align(Alignment.CenterEnd)
                .padding(11.dp)
                .clickable {
                    onEvent(TaskEvent.SetTitle(taskTitle))
                    onEvent(TaskEvent.SetDescription(taskDescription))
                    onEvent(TaskEvent.SetDueDateDay(dueDateDay))
                    onEvent(TaskEvent.SetDueDateMonth(dueDateMonth))
                    onEvent(TaskEvent.SetDueDateYear(dueDateYear))
                    onEvent(TaskEvent.SetDueDateHour(dueHour))
                    onEvent(TaskEvent.SetDueDateMinute(dueMinute))
                    if (showReminderDate1) {
                        onEvent(TaskEvent.SetReminder1Day(reminder1Day))
                        onEvent(TaskEvent.SetReminder1Month(reminder1Month))
                        onEvent(TaskEvent.SetReminder1Year(reminder1Year))
                        onEvent(TaskEvent.SetReminder1Hour(reminder1Hour))
                        onEvent(TaskEvent.SetReminder1Minute(reminder1Minute))
                    }
                    if (showReminderDate2) {
                        onEvent(TaskEvent.SetReminder2Day(reminder2Day))
                        onEvent(TaskEvent.SetReminder2Month(reminder2Month))
                        onEvent(TaskEvent.SetReminder2Year(reminder2Year))
                        onEvent(TaskEvent.SetReminder2Hour(reminder2Hour))
                        onEvent(TaskEvent.SetReminder2Minute(reminder2Minute))
                    }
                    if (showReminderDate3) {
                        onEvent(TaskEvent.SetReminder3Day(reminder3Day))
                        onEvent(TaskEvent.SetReminder3Month(reminder3Month))
                        onEvent(TaskEvent.SetReminder3Year(reminder3Year))
                        onEvent(TaskEvent.SetReminder3Hour(reminder3Hour))
                        onEvent(TaskEvent.SetReminder3Minute(reminder3Minute))
                    }

                    if (showReminderDate1) {
                        val alarmItem1 = AlarmItem(
                            time = LocalDateTime.of(reminder1Year, reminder1Month, reminder1Day, reminder1Hour, reminder1Minute, 0),
                            id = editTaskId * 10 + 2,
                            title = taskTitle,
                            dueDateDay = dueDateDay,
                            dueDateMonth = dueDateMonth,
                            dueDateHour = dueHour,
                            dueDateMinute = dueMinute
                        )
                        alarmItem1.let(scheduler::schedule)
                    }

                    if (showReminderDate2) {
                        val alarmItem2 = AlarmItem(
                            time = LocalDateTime.of(reminder2Year, reminder2Month, reminder2Day, reminder2Hour, reminder2Minute, 0),
                            id = editTaskId * 10 + 3,
                            title = taskTitle,
                            dueDateDay = dueDateDay,
                            dueDateMonth = dueDateMonth,
                            dueDateHour = dueHour,
                            dueDateMinute = dueMinute
                        )
                        alarmItem2.let(scheduler::schedule)
                    }

                    if (showReminderDate3) {
                        val alarmItem3 = AlarmItem(
                            time = LocalDateTime.of(reminder3Year, reminder3Month, reminder3Day, reminder3Hour, reminder3Minute, 0),
                            id = editTaskId * 10 + 4,
                            title = taskTitle,
                            dueDateDay = dueDateDay,
                            dueDateMonth = dueDateMonth,
                            dueDateHour = dueHour,
                            dueDateMinute = dueMinute
                        )
                        alarmItem3.let(scheduler::schedule)
                    }

                    val alarmItem = AlarmItem(
                        time = LocalDateTime.of(dueDateYear, dueDateMonth, dueDateDay, dueHour, dueMinute, 5),
                        id = editTaskId * 10 + 1,
                        title = taskTitle,
                        dueDateDay = dueDateDay,
                        dueDateMonth = dueDateMonth,
                        dueDateHour = dueHour,
                        dueDateMinute = dueMinute
                    )
                    alarmItem.let(scheduler::schedule)


                    onEvent(TaskEvent.SaveTask)
                    navController.popBackStack()
                },
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.ic_baseline_save_30),
                contentDescription = stringResource(id = R.string.add_task)
            )
        }

        Column(modifier = Modifier.padding(start = 10.dp, end = 15.dp)) {

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
                imeAction = ImeAction.Done,
            ){
                taskDescription = it
            }

            Text(
                modifier = Modifier.padding(start = 15.dp, top = 10.dp),
                text = context.resources.getString(R.string.deadline),
                style = Typography.body2
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
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
                            daysAmount = getMonthDaysAmount(dueDateMonth - 1, dueDateYear)
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
                            daysAmount = getMonthDaysAmount(dueDateMonth - 1, dueDateYear)
                        }
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
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
                        range = 0..59,
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
                    .clickable {
                        showReminderDate1 = !showReminderDate1
                    },
                text = context.resources.getString(R.string.addReminder),
                style = Typography.body2
            )
            if (showReminderDate1) {
                var reminder1DaysAmount = getMonthDaysAmount(reminder1Month - 1, reminder1Year)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
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
                                reminder1DaysAmount = getMonthDaysAmount(reminder1Month - 1, reminder1Year)
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
                                reminder1DaysAmount = getMonthDaysAmount(reminder1Month - 1, reminder1Year)
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
                            range = 0..59,
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
                        .clickable {
                            showReminderDate2 = !showReminderDate2
                        },
                    text = context.resources.getString(R.string.addReminder),
                    style = Typography.body2
                )
                if (showReminderDate2) {
                    var reminder2DaysAmount = getMonthDaysAmount(reminder2Month - 1, reminder2Year)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
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
                                    reminder2DaysAmount = getMonthDaysAmount(reminder2Month - 1, reminder2Year)
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
                                    reminder2DaysAmount = getMonthDaysAmount(reminder2Month - 1, reminder2Year)
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
                                range = 0..59,
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
                            .clickable {
                                showReminderDate3 = !showReminderDate3
                            },
                        text = context.resources.getString(R.string.addReminder),
                        style = Typography.body2
                    )
                    if (showReminderDate3) {
                        var reminder3DaysAmount = getMonthDaysAmount(reminder3Month - 1, reminder3Year)

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp),
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
                                            getMonthDaysAmount(reminder3Month - 1, reminder3Year)
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
                                            getMonthDaysAmount(reminder3Month - 1, reminder3Year)
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
                                    range = 0..59,
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
}