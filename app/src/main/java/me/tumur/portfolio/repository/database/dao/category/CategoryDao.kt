package me.tumur.portfolio.repository.database.dao.category

import androidx.paging.DataSource
import androidx.room.*
import me.tumur.portfolio.repository.database.model.category.CategoryModel
import me.tumur.portfolio.utils.constants.DbConstants

@Dao
abstract class CategoryDao {

    /** Update */
    @Transaction
    open suspend fun update(data: List<CategoryModel>): List<Long> {
        delete()
        return insert(data)
    }

    /** Insert */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(data: List<CategoryModel>): List<Long>

    /** Delete */
    @Query(DbConstants.DELETE_CATEGORY)
    abstract suspend fun delete()

    /** Get button by owner id */
    @Query(DbConstants.GET_CATEGORY_BY_TYPE)
    abstract fun getByGroup(type: Int): DataSource.Factory<Int, CategoryModel>
}