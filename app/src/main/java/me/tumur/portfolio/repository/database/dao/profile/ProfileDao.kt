package me.tumur.portfolio.repository.database.dao.profile

import androidx.lifecycle.LiveData
import androidx.room.*
import me.tumur.portfolio.repository.database.model.profile.ProfileModel
import me.tumur.portfolio.utils.constants.DbConstants

@Dao
abstract class ProfileDao {

    /** Update */
    @Transaction
    open suspend fun update(data: List<ProfileModel>){
        delete()
        insert(data)
    }

    /** Delete */
    @Query(DbConstants.DELETE_PROFILE)
    abstract suspend fun delete()

    /** Insert */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(data: List<ProfileModel>)

    /** Get by id */
    @Query(DbConstants.GET_PROFILE_BY_ID)
    abstract fun getById(id: String): LiveData<ProfileModel>


}