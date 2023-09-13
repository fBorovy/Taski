package pl.fboro.taski

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import pl.fboro.taski.feature_calendar.Calendar
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
                            .padding(horizontal = 15.dp)
                    )
                    {
                        Calendar()
                    }
                }
            }
        }
    }
}