package pl.fboro.taski.feature_task.presentation.components

import androidx.compose.foundation.background
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import pl.fboro.taski.feature_task.data.TaskEvent
import pl.fboro.taski.feature_task.domain.model.Task
import pl.fboro.taski.ui.theme.BackgroundColor

@Composable
fun DeleteTaskDialog(
    task: Task,
    onDismissRequest: (Boolean) -> Unit,
    onEvent: (TaskEvent) -> Unit,
) {
    AlertDialog(
        modifier = Modifier.background(BackgroundColor),
        backgroundColor = Color(0xFFF9F6D6),
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
