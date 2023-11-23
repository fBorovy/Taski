package pl.fboro.taski.feature_task.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import pl.fboro.taski.feature_task.domain.model.Task

@Dao
interface TaskDao {

    @Query("DELETE FROM sqlite_sequence")
    fun clearPrimaryKeyIndex()

    @Upsert
    suspend fun upsertTask(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM Task WHERE (isDone IS 1) ORDER BY dueDateYear, dueDateMonth, dueDateDay, dueDateHour, dueDateMinute")
    fun getAllDoneTasks(): Flow<List<Task>>

    @Query("SELECT * FROM Task WHERE (isDone IS 0) ORDER BY dueDateYear, dueDateMonth, dueDateDay, dueDateHour, dueDateMinute")
    fun getAllUndoneTasks(): Flow<List<Task>>

    @Query("SELECT * FROM Task ORDER BY dueDateYear, dueDateMonth, dueDateDay, dueDateHour, dueDateMinute")
    fun getAllTasks(): Flow<List<Task>>

    @Query("SELECT * FROM Task WHERE dueDateDay = :day AND dueDateMonth = :month AND dueDateYear = :year ORDER BY dueDateYear, dueDateMonth, dueDateDay, dueDateHour, dueDateMinute")
    fun getSpecifiedDayAllTasks(day: Int, month: Int, year:Int): Flow<List<Task>>

    @Query("SELECT * FROM Task WHERE dueDateDay = :day AND dueDateMonth = :month AND dueDateYear = :year AND (isDone IS 1) ORDER BY dueDateYear, dueDateMonth, dueDateDay, dueDateHour, dueDateMinute")
    fun getSpecifiedDayDoneTask(day: Int, month: Int, year:Int): Flow<List<Task>>

    @Query("SELECT * FROM Task WHERE dueDateDay = :day AND dueDateMonth = :month AND dueDateYear = :year AND (isDone IS 0) ORDER BY dueDateYear, dueDateMonth, dueDateDay, dueDateHour, dueDateMinute")
    fun getSpecifiedDayUndoneTask(day: Int, month: Int, year:Int): Flow<List<Task>>

    @Query("Select * FROM Task WHERE id = :taskId")
    suspend fun getTaskById(taskId: Int): Task?
}