package pl.fboro.taski.feature_task.presentation.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pl.fboro.taski.R
import pl.fboro.taski.Screen
import pl.fboro.taski.feature_task.data.TaskEvent
import pl.fboro.taski.feature_task.domain.model.Task
import pl.fboro.taski.feature_task.utils.*
import pl.fboro.taski.ui.theme.Typography

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskItem(
    task: Task,
    navController: NavController,
    onEvent: (TaskEvent) -> Unit,
) {

    val thumbnailDescriptionLinesAmount = 3
    var showDeleteDialog by remember{ mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(bottom = 32.dp)
            .combinedClickable(
                onClick = {
                    editTaskId = task.id
                    editTitle = task.title
                    editDescription = task.description
                    editDueDateDay = task.dueDateDay
                    editDueDateMonth = task.dueDateMonth
                    editDueDateYear = task.dueDateYear
                    editDueDateHour = task.dueDateHour
                    editDueDateMinute = task.dueDateMinute
                    editReminder1Day = task.reminder1Day
                    editReminder1Month = task.reminder1Month
                    editReminder1Year = task.reminder1Year
                    editReminder1Hour = task.reminder1Hour
                    editReminder1Minute = task.reminder1Minute
                    editReminder2Day = task.reminder2Day
                    editReminder2Month = task.reminder2Month
                    editReminder2Year = task.reminder2Year
                    editReminder2Hour = task.reminder2Hour
                    editReminder2Minute = task.reminder2Minute
                    editReminder3Day = task.reminder3Day
                    editReminder3Month = task.reminder3Month
                    editReminder3Year = task.reminder3Year
                    editReminder3Hour = task.reminder3Hour
                    editReminder3Minute = task.reminder3Minute
                    editIsDone = task.isDone
                    navController.navigate(route = Screen.EditTaskScreen.route)
                },
                onLongClick = {
                    showDeleteDialog = true
                }
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 3.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            val month = if (task.dueDateMonth < 10) "0${task.dueDateMonth}" else "${task.dueDateMonth}"
            val minute = if (task.dueDateMinute < 10) "0${task.dueDateMinute}" else "${task.dueDateMinute}"
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if(task.isDone) {
                    Image(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.ic_baseline_done_30),
                        contentDescription = stringResource(id = R.string.done),
                    )
                }
                Text(
                    modifier = Modifier.padding(start = 5.dp),
                    text = task.title,
                    style = Typography.body1,
                )
            }

            Text(text = "${task.dueDateDay}.$month.${task.dueDateYear}" +
                    " ${task.dueDateHour}:$minute",
                style = Typography.body1)
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Black)
            .padding(bottom = 3.dp)
        )
        Text(
            text = task.description,
            maxLines = thumbnailDescriptionLinesAmount,
            overflow = TextOverflow.Ellipsis,
            style = Typography.body1
        )
    }

    if (showDeleteDialog) {
        DeleteTaskDialog(
            task = task,
            onDismissRequest = { showDeleteDialog = it },
            onEvent = onEvent,
        )
    }
}