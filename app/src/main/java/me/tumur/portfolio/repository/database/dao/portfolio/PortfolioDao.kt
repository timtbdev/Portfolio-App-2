package me.tumur.portfolio.repository.database.dao.portfolio

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import me.tumur.portfolio.repository.database.model.portfolio.PortfolioModel
import me.tumur.portfolio.utils.constants.DbConstants

@Dao
abstract class PortfolioDao {

    /** Update */
    @Transaction
    open suspend fun update(data: List<PortfolioModel>): List<Long> {
        delete()
        return insert(data)
    }

    /** Insert */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(data: List<PortfolioModel>): List<Long>

    /** Delete */
    @Query(DbConstants.DELETE_PORTFOLIO)
    abstract suspend fun delete()

    /** Get portfolio list by owner id */
    @Query(DbConstants.GET_PORTFOLIO_LIST_BY_OWNER_ID)
    abstract fun getListByOwnerId(id: String): DataSource.Factory<Int, PortfolioModel>


    /** Get portfolio item by id*/
    @Query(DbConstants.GET_PORTFOLIO_ITEM_BY_ID)
    abstract fun getById(id: String): LiveData<PortfolioModel>

//    /** Search by query */
//    @Query(DbConstants.GET_PORTFOLIO_BY_QUERY)
//    abstract fun getByQuery(query: String): LiveData<List<PortfolioModel>>

}