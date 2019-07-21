package me.tumur.portfolio.repository.database.dao.task

import androidx.room.*
import me.tumur.portfolio.repository.database.model.task.TaskModel
import me.tumur.portfolio.utils.constants.DbConstants

@Dao
abstract class TaskDao {

    /** Update */
    @Transaction
    open suspend fun update(data: List<TaskModel>): List<Long> {
        delete()
        return insert(data)
    }

    /** Insert */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(data: List<TaskModel>): List<Long>

    /** Delete */
    @Query(DbConstants.DELETE_TASK)
    abstract suspend fun delete()
}