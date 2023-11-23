package pl.fboro.taski.feature_task.presentation.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import pl.fboro.taski.feature_task.data.TaskEvent
import pl.fboro.taski.feature_task.domain.model.Task

@Composable
fun DeleteTaskDialog(
    task: Task,
    onDismissRequest: (Boolean) -> Unit,
    onEvent: (TaskEvent) -> Unit,
) {
    AlertDialog(
        onDismissRequest = { onDismissRequest(false) },
        title = { Text("Usunąć task ${task.title}?") },
        text = { Text("Tej operacji nie da się cofnąć") },
        confirmButton = {
            TextButton(onClick = {
                onEvent(TaskEvent.DeleteTask(task))
                onDismissRequest(false)
            }) {
                Text("Usuń")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest(false) }) {
                Text("Cofnij")
            }
        },
    )
}
