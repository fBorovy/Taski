package pl.fboro.taski

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pl.fboro.taski.feature_calendar.Calendar
import pl.fboro.taski.feature_task.presentation.TasksContent
import pl.fboro.taski.ui.TopPanel
import pl.fboro.taski.ui.theme.AddTaskButton
import pl.fboro.taski.ui.theme.Background
import pl.fboro.taski.ui.theme.TaskiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskiTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background)
                {
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
            }
        }
    }
}