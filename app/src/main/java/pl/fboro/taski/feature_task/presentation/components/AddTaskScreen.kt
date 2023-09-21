package pl.fboro.taski.feature_task.presentation.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pl.fboro.taski.R
import pl.fboro.taski.ui.theme.Background


@Composable
fun AddTaskScreen(navController: NavController) {
    val context: Context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(
                colors = listOf(Color.White, Background),
                startY = -100f
            ))
            .padding(top = 20.dp)
    ) {
        var taskTitle by remember{ mutableStateOf("") }
        var taskDescription by remember{ mutableStateOf("") }


        TransparentTextField(
            value = taskTitle,
            placeholder = context.resources.getString(R.string.title_placeholder),
            singleLine = true,
            imeAction = ImeAction.Next,
        ){
            taskTitle = it
        }

        TransparentTextField(
            value = taskDescription,
            placeholder = context.resources.getString(R.string.description_placeholder),
            singleLine = false,
            imeAction = ImeAction.Next,
        ){
            taskDescription = it
        }
    }

}