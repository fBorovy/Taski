package pl.fboro.taski.feature_task.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import pl.fboro.taski.R
import pl.fboro.taski.feature_task.domain.model.Task
import pl.fboro.taski.ui.theme.Typography

@Composable
fun TaskItem(task: Task) {

    val thumbnailDescriptionLinesAmount = 3

    Column(modifier = Modifier.padding(bottom = 32.dp)) {
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
                //if(task.isDone) {
                    Image(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.ic_baseline_done_30),
                        contentDescription = stringResource(id = R.string.done),
                    )
                //}
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



//    Column(
//        modifier = Modifier.fillMaxWidth()
//    ){
//        Row(
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            if(task.isDone) {
//                Box(
//                    modifier = Modifier
//                        .weight(1f)
//                ) {
//                    Image(
//                        modifier = Modifier.fillMaxSize(),
//                        painter = painterResource(id = R.drawable.ic_baseline_done_30),
//                        contentDescription = stringResource(id = R.string.done),
//                    )
//                }
//            }
//            Text(
//                modifier = Modifier.weight(2f),
//                text = task.title,
//                style = Typography.body1,
//            )
//            Text(
//                modifier = Modifier.weight(2f),
//                text =  "${task.dueDateDay}." +
//                        "${task.dueDateMonth}." +
//                        "${task.dueDateYear} " +
//                        "${task.dueDateHour}:" +
//                        "${task.dueDateMinute}",
//                style = Typography.body1
//            )
//        }
//        Divider(modifier = Modifier.background(Color.Black), thickness = 1.dp)
//        Text(
//            text = task.description,
//            maxLines = thumbnailDescriptionLinesAmount,
//            overflow = TextOverflow.Ellipsis,
//            style = Typography.body1
//        )
//    }
}