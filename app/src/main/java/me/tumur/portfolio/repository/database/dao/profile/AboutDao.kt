package me.tumur.portfolio.repository.database.dao.profile

import androidx.lifecycle.LiveData
import androidx.room.*
import me.tumur.portfolio.repository.database.model.profile.AboutModel
import me.tumur.portfolio.utils.constants.DbConstants

@Dao
abstract class AboutDao {

    /** Update */
    @Transaction
    open suspend fun update(about: List<AboutModel>) {
        delete()
        insert(about)
    }

    /** Insert */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(about: List<AboutModel>): List<Long>

    /** Delete */
    @Query(DbConstants.DELETE_ABOUT)
    abstract suspend fun delete()

    /** Get */
    @Query(DbConstants.GET_ABOUT_BY_OWNER_ID)
    abstract fun getById(id: String): LiveData<List<AboutModel>>
}