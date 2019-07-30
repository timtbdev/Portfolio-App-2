package me.tumur.portfolio.repository.database.dao.button

import androidx.paging.DataSource
import androidx.room.*
import me.tumur.portfolio.repository.database.model.button.ButtonModel
import me.tumur.portfolio.utils.constants.DbConstants

@Dao
abstract class ButtonDao {

    /** Update */
    @Transaction
    open suspend fun update(data: List<ButtonModel>): List<Long> {
        delete()
        return insert(data)
    }

    /** Insert */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(data: List<ButtonModel>): List<Long>

    /** Delete */
    @Query(DbConstants.DELETE_BUTTON)
    abstract suspend fun delete()

    /** Get button by owner id */
    @Query(DbConstants.GET_BUTTON_BY_OWNER_ID)
    abstract fun getById(id: String): DataSource.Factory<Int, ButtonModel>
}