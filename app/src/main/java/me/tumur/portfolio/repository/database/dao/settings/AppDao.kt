package me.tumur.portfolio.repository.database.dao.settings

import androidx.lifecycle.LiveData
import androidx.room.*
import me.tumur.portfolio.repository.database.model.settings.AppModel
import me.tumur.portfolio.utils.constants.DbConstants

@Dao
abstract class AppDao {

    /** Update */
    @Transaction
    open suspend fun update(list: List<AppModel>) {
        delete()
        insert(list)
    }

    /** Insert */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(list: List<AppModel>): List<Long>

    /** Delete */
    @Query(DbConstants.DELETE_APP)
    abstract suspend fun delete()

    /** Get */
    @Query(DbConstants.GET_APP)
    abstract fun get(): LiveData<List<AppModel>>
}