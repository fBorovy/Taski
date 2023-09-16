package pl.fboro.taski.feature_task.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import pl.fboro.taski.R
import pl.fboro.taski.feature_task.domain.model.Task

@Composable
fun TaskItem(task: Task) {

    val thumbnailDescriptionLinesAmount = 3

    Column(
        modifier = Modifier.fillMaxWidth()
    ){
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                if(task.isDone) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.ic_baseline_done_30),
                        contentDescription = stringResource(id = R.string.done),
                    )
                }
            }
            Text(
                modifier = Modifier.weight(2f),
                text = task.title,
            )
            Text(
                modifier = Modifier.weight(2f),
                text =  "${task.dueDate.day}." +
                        "${task.dueDate.month}." +
                        "${task.dueDate.year} " +
                        "${task.dueDate.hour}:" +
                        "${task.dueDate.minute}"
            )
        }
        Divider(thickness = 1.dp)
        Text(
            text = task.description,
            maxLines = thumbnailDescriptionLinesAmount,
            overflow = TextOverflow.Ellipsis
        )
    }

}