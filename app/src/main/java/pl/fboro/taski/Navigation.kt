package pl.fboro.taski

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pl.fboro.taski.feature_task.data.TaskEvent
import pl.fboro.taski.feature_task.data.TaskState
import pl.fboro.taski.feature_task.presentation.components.AddTaskScreen

@Composable
fun Navigation(
    state: TaskState,
    onEvent: (TaskEvent) -> Unit,
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.MainScreen.name) {
        composable(
            route = Screen.MainScreen.name
        ) {
            MainScreen(navController, state, onEvent)
        }
        composable(
            route = Screen.AddTaskScreen.name
        ) {
            AddTaskScreen(navController, onEvent)
        }
    }

}
