package me.tumur.portfolio.repository.database.dao.welcome

import androidx.lifecycle.LiveData
import androidx.room.*
import me.tumur.portfolio.repository.database.model.welcome.WelcomeModel
import me.tumur.portfolio.utils.constants.DbConstants


@Dao
abstract class WelcomeDao {

    /** Update */
    @Transaction
    open suspend fun update(data: List<WelcomeModel>): List<Long> {
        delete()
        return insert(data)
    }

    /** Check */
    @Query(DbConstants.CHECK_WELCOME)
    abstract suspend fun check(): Int

    /** Insert */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(data: List<WelcomeModel>): List<Long>

    /** Delete */
    @Query(DbConstants.DELETE_WELCOME)
    abstract suspend fun delete()

    /** Get */
    @Query(DbConstants.GET_ALL_WELCOME)
    abstract fun get(): LiveData<List<WelcomeModel>>

    /** Get by position */
    @Query(DbConstants.GET_WELCOME_BY_ID)
    abstract fun getById(id: String): LiveData<WelcomeModel>
}