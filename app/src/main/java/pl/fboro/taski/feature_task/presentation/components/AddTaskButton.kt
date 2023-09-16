package pl.fboro.taski.feature_task.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pl.fboro.taski.R

@Composable
fun AddTaskButton() {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(shape = RoundedCornerShape(60.dp, 0.dp, 0.dp, 60.dp))
                .background(pl.fboro.taski.ui.theme.AddTaskButton)
                .align(Alignment.CenterEnd)
                .clickable {
                    //navController.navigate()
                },
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.ic_baseline_add_30),
                contentDescription = stringResource(id = R.string.add_task)
            )
        }
    }

}