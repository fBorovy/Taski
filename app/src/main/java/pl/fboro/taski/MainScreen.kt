package pl.fboro.taski

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pl.fboro.taski.feature_calendar.presentation.Calendar
import pl.fboro.taski.feature_task.data.TaskEvent
import pl.fboro.taski.feature_task.data.TaskState
import pl.fboro.taski.feature_task.presentation.components.AddTaskButton
import pl.fboro.taski.feature_task.presentation.components.TasksContent
import pl.fboro.taski.ui.TopPanel
import pl.fboro.taski.ui.theme.BackgroundColor

@Composable
fun MainScreen(
    navController: NavController,
    state: TaskState,
    onEvent: (TaskEvent) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.White, BackgroundColor),
                    startY = -100f
                )
            )
            .padding(top = 20.dp)
    ){
        AddTaskButton(navController)
        Column(
            modifier = Modifier
                .padding(horizontal = 15.dp)
        ) {
            TopPanel()
            Calendar(state, onEvent)
            TasksContent(state, navController, onEvent)
        }
    }
}