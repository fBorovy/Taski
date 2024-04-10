package pl.fboro.taski.feature_task.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pl.fboro.taski.feature_task.data.PresentationMode
import pl.fboro.taski.feature_task.data.TaskEvent
import pl.fboro.taski.feature_task.data.TaskState

@Composable
fun TasksContent(
    state: TaskState,
    navController: NavController,
    onEvent: (TaskEvent) -> Unit
) {
    val context = LocalContext.current
    var category by remember{ mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .clickable {
                    if (category < 2) category += 1 else category = 0
                    if (state.selectedDueDate[0] == 0) {
                        onEvent(
                            TaskEvent.ChangePresentationMode(mode = when (category) {
                                0 -> PresentationMode.SHOW_UNDONE
                                1 -> PresentationMode.SHOW_DONE
                                else -> PresentationMode.SHOW_ALL
                            }))
                    } else {
                        onEvent(TaskEvent.ChangePresentationMode(mode = when (category) {
                            0 -> PresentationMode.SHOW_UNDONE_SPECIFIED_DATE
                            1 -> PresentationMode.SHOW_DONE_SPECIFIED_DATE
                            else -> PresentationMode.SHOW_ALL_SPECIFIED_DATE
                        }))
                    }
                }
        ) {
            Text(
                text = when (category){
                    0 -> "Nadchodzące"
                    1 -> "Zrobione"
                    2 -> "Wszystkie"
                    else -> "Błąd"
            })
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = painterResource(id = pl.fboro.taski.R.drawable.ic_cached_30),
                contentDescription = context.resources.getString(pl.fboro.taski.R.string.change_task_showing_option_image)
            )
        }

        Column(modifier = Modifier
            .padding(horizontal = 15.dp)
            .verticalScroll(rememberScrollState())
        ) {
            for (task in state.tasks) {
                TaskItem(task, navController, onEvent)
            }
            
        }
    }


}