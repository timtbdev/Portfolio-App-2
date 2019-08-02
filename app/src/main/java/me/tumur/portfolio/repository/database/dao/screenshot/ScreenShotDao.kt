package me.tumur.portfolio.repository.database.dao.screenshot

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import me.tumur.portfolio.repository.database.model.screenshot.ScreenShotModel
import me.tumur.portfolio.utils.constants.DbConstants

@Dao
abstract class ScreenShotDao {

    /** Update */
    @Transaction
    open suspend fun update(list: List<ScreenShotModel>): List<Long> {
        delete()
        return insert(list)
    }

    /** Insert */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(list: List<ScreenShotModel>): List<Long>

    /** Delete */
    @Query(DbConstants.SCREENSHOT_DELETE)
    abstract suspend fun delete()

    /** Get paged list items */
    @Query(DbConstants.SCREENSHOT_GET_LIST_ITEMS)
    abstract fun getPagedListItems(id: String): DataSource.Factory<Int, ScreenShotModel>

    /** Get list items */
    @Query(DbConstants.SCREENSHOT_GET_LIST_ITEMS)
    abstract fun getListItems(id: String): LiveData<List<ScreenShotModel>>
}