package me.tumur.portfolio.repository.database.dao.resource

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import me.tumur.portfolio.repository.database.model.resource.ResourceModel
import me.tumur.portfolio.utils.constants.DbConstants

@Dao
abstract class ResourceDao {

    /** Update */
    @Transaction
    open suspend fun update(list: List<ResourceModel>) {
        delete()
        insert(list)
    }

    /** Delete */
    @Query(DbConstants.RESOURCE_DELETE)
    abstract suspend fun delete()

    /** Insert */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(list: List<ResourceModel>)

    /** Get list items */
    @Query(DbConstants.RESOURCE_GET_LIST_ITEMS)
    abstract fun getListItems(id: String): DataSource.Factory<Int, ResourceModel>

    /** Check table */
    @Query(DbConstants.RESOURCE_CHECK)
    abstract fun check(): LiveData<Int>


}