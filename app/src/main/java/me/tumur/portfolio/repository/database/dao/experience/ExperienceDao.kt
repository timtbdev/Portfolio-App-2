package me.tumur.portfolio.repository.database.dao.experience

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import me.tumur.portfolio.repository.database.model.experience.ExperienceModel
import me.tumur.portfolio.utils.constants.DbConstants

@Dao
abstract class ExperienceDao {

    /** Update */
    @Transaction
    open suspend fun update(list: List<ExperienceModel>): List<Long> {
        delete()
        return insert(list)
    }

    /** Insert */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(list: List<ExperienceModel>): List<Long>

    /** Delete */
    @Query(DbConstants.EXPERIENCE_DELETE)
    abstract suspend fun delete()

    /** Get list items */
    @Query(DbConstants.EXPERIENCE_GET_LIST_ITEMS)
    abstract fun getListItems(id: String): DataSource.Factory<Int, ExperienceModel>


    /** Get single item */
    @Query(DbConstants.EXPERIENCE_GET_SINGLE_ITEM)
    abstract fun getSingleItem(id: String): LiveData<ExperienceModel>
}