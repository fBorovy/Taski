package pl.fboro.taski.feature_task.data

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.fboro.taski.feature_task.domain.model.Task

@Database(
    entities = [Task::class],
    version = 1
)
abstract class TaskDatabase: RoomDatabase() {

    abstract val dao: TaskDao
}