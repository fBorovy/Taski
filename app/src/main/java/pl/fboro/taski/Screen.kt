package pl.fboro.taski

sealed class Screen(val route: String = "") {
    object MainScreen: Screen(route = "main_screen")
    object AddTaskScreen: Screen(route = "add_task")
    object EditTaskScreen: Screen(route = "edit_task")
}