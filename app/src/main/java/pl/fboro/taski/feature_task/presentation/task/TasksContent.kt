package pl.fboro.taski.feature_task.presentation.task

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pl.fboro.taski.feature_task.data.PresentationMode
import pl.fboro.taski.feature_task.data.TaskEvent
import pl.fboro.taski.feature_task.data.TaskState
import pl.fboro.taski.feature_task.presentation.components.TaskItem

@Composable
fun TasksContent(
    state: TaskState,
    onEvent: (TaskEvent) -> Unit
) {
    var category by remember{ mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .padding(20.dp)
                .clickable {
                    if (category < 2) category += 1 else category = 0
                    if (state.selectedDueDate[0] == 0) { onEvent(
                        TaskEvent.ChangePresentationMode( mode = when (category) {
                            0 -> PresentationMode.SHOW_UNDONE
                            1 -> PresentationMode.SHOW_DONE
                            else -> PresentationMode.SHOW_ALL
                        }))
                    } else {
                        onEvent( TaskEvent.ChangePresentationMode( mode = when (category) {
                            0 -> PresentationMode.SHOW_UNDONE_SPECIFIED_DATE
                            1 -> PresentationMode.SHOW_DONE_SPECIFIED_DATE
                            else -> PresentationMode.SHOW_ALL_SPECIFIED_DATE
                        }))
                    }
                },
            text = when (category){
            0 -> "Nadchodzące"
            1 -> "Zrobione"
            2 -> "Wszystkie"
            else -> "Błąd"
        })
        Column(modifier = Modifier.padding(horizontal = 15.dp),
        ) {
            for (task in state.tasks) {
                TaskItem(task = task)
            }
            
        }
    }


}