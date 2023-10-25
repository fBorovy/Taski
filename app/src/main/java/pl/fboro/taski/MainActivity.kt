package pl.fboro.taski

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import pl.fboro.taski.feature_task.data.TaskDatabase
import pl.fboro.taski.feature_task.presentation.TaskViewModel
import pl.fboro.taski.ui.theme.TaskiTheme

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java,
            "tasks.db",
        ).build()
    }

    private val viewModel by viewModels<TaskViewModel>(
        factoryProducer = {
            object: ViewModelProvider.Factory {
                override fun <T: ViewModel> create(modelClass: Class<T>): T {
                    return TaskViewModel(db.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskiTheme {
                val state by viewModel.state.collectAsState()
                Navigation(state)
            }
        }
    }
}