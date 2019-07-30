package me.tumur.portfolio.repository.database.dao.experience

import androidx.room.*
import me.tumur.portfolio.repository.database.model.experience.ExperienceModel
import me.tumur.portfolio.utils.constants.DbConstants

@Dao
abstract class ExperienceDao {

    /** Update */
    @Transaction
    open suspend fun update(data: List<ExperienceModel>): List<Long> {
        delete()
        return insert(data)
    }

    /** Insert */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(data: List<ExperienceModel>): List<Long>

    /** Delete */
    @Query(DbConstants.DELETE_EXPERIENCE)
    abstract suspend fun delete()
}