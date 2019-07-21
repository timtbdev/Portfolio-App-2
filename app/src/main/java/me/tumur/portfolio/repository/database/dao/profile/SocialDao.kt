package me.tumur.portfolio.repository.database.dao.profile

import androidx.lifecycle.LiveData
import androidx.room.*
import me.tumur.portfolio.repository.database.model.profile.AboutModel
import me.tumur.portfolio.repository.database.model.profile.SocialModel
import me.tumur.portfolio.utils.constants.DbConstants

@Dao
abstract class SocialDao {

    /** Update */
    @Transaction
    open suspend fun update(social: List<SocialModel>) {
        delete()
        insert(social)
    }

    /** Insert */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(social: List<SocialModel>): List<Long>

    /** Delete */
    @Query(DbConstants.DELETE_SOCIAL)
    abstract suspend fun delete()

    /** Get */
    @Query(DbConstants.GET_SOCIAL_BY_OWNER_ID)
    abstract fun get(id: String): LiveData<List<SocialModel>>
}