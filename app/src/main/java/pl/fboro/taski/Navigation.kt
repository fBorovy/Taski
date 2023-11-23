package pl.fboro.taski

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pl.fboro.taski.feature_task.data.TaskEvent
import pl.fboro.taski.feature_task.data.TaskState
import pl.fboro.taski.feature_task.presentation.components.AddTaskScreen
import pl.fboro.taski.feature_task.presentation.components.EditTaskScreen

@Composable
fun Navigation(
    state: TaskState,
    onEvent: (TaskEvent) -> Unit,
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(
            route = Screen.MainScreen.route
        ) {
            MainScreen(navController, state, onEvent)
        }
        composable(
            route = Screen.AddTaskScreen.route
        ) {
            AddTaskScreen(navController, onEvent)
        }
        composable(
            route = Screen.EditTaskScreen.route
        ) {
            EditTaskScreen(navController, onEvent)
        }
    }

}
