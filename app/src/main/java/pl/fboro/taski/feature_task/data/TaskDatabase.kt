package pl.fboro.taski.feature_task.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pl.fboro.taski.feature_task.domain.model.Task
import pl.fboro.taski.feature_task.utils.MyDateConverter

@Database(
    entities = [Task::class],
    version = 1
)
@TypeConverters(MyDateConverter::class)
abstract class TaskDatabase: RoomDatabase() {

    abstract val dao: TaskDao
}