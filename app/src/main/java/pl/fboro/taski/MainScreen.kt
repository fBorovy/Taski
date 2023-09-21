package pl.fboro.taski

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pl.fboro.taski.feature_calendar.presentation.Calendar
import pl.fboro.taski.feature_task.presentation.TasksContent
import pl.fboro.taski.ui.TopPanel
import pl.fboro.taski.ui.theme.AddTaskButton
import pl.fboro.taski.ui.theme.Background

@Composable
fun MainScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(
                colors = listOf(Color.White, Background),
                startY = -100f
            ))
            .padding(top = 20.dp)
    ){
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(shape = RoundedCornerShape(60.dp, 0.dp, 0.dp, 60.dp))
                .background(AddTaskButton)
                .align(Alignment.CenterEnd)
                .clickable {
                    navController.navigate(Screen.AddTaskScreen.name)
                },
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.ic_baseline_add_30),
                contentDescription = stringResource(id = R.string.add_task)
            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 15.dp)
        )


        {
            TopPanel()
            Calendar()
            TasksContent()
        }
    }
}