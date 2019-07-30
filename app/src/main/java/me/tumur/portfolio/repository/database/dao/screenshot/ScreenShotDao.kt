package me.tumur.portfolio.repository.database.dao.screenshot

import androidx.paging.DataSource
import androidx.room.*
import me.tumur.portfolio.repository.database.model.screenshot.ScreenShotModel
import me.tumur.portfolio.utils.constants.DbConstants

@Dao
abstract class ScreenShotDao {

    /** Update */
    @Transaction
    open suspend fun update(data: List<ScreenShotModel>): List<Long> {
        delete()
        return insert(data)
    }

    /** Insert */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(data: List<ScreenShotModel>): List<Long>

    /** Delete */
    @Query(DbConstants.DELETE_SCREENSHOT)
    abstract suspend fun delete()

    /** Get button by owner id */
    @Query(DbConstants.GET_SCREENSHOT_BY_OWNER_ID)
    abstract fun getByOwnerId(id: String): DataSource.Factory<Int, ScreenShotModel>
}