package pl.fboro.taski.feature_task.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import pl.fboro.taski.feature_calendar.utils.MyDate
import pl.fboro.taski.feature_task.domain.model.Task

@Dao
interface TaskDao {

    @Upsert
    suspend fun upsertTask(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM Task WHERE isDone IS TRUE ORDER BY dueDate")
    fun getAllDoneTasks(): Flow<List<Task>>

    @Query("SELECT * FROM Task WHERE isDone IS False ORDER BY dueDate")
    fun getAllUndoneTasks(): Flow<List<Task>>

    @Query("SELECT * FROM Task ORDER BY dueDate")
    fun getAllTasks(): Flow<List<Task>>

    @Query("SELECT * FROM Task WHERE dueDate = :dueDate ORDER BY dueDate")
    fun getSpecifiedDayAllActivities(dueDate: MyDate): Flow<List<Task>>

    @Query("SELECT * FROM Task WHERE dueDate = :dueDate AND isDone IS TRUE ORDER BY dueDate")
    fun getSpecifiedDayDoneTask(dueDate: MyDate): Flow<List<Task>>

    @Query("SELECT * FROM Task WHERE dueDate = :dueDate AND isDone IS FALSE ORDER BY dueDate")
    fun getSpecifiedDayUndoneTask(dueDate: MyDate): Flow<List<Task>>
}