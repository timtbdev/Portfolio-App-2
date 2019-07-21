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

//    /** Get item by id */
//    @Query(DbConstants.GET_FULL_PORTFOLIO_BY_ID)
//    abstract fun getItemById(id: String): LiveData<PortfolioFullModel>

    /** Get list by id */
    @Query(DbConstants.GET_PORTFOLIO_LIST_BY_OWNER_ID)
    abstract fun getListById(id: String): LiveData<List<PortfolioModel>>

    /** Get item by id  and tab */
    @Query(DbConstants.GET_PORTFOLIO_DATA_BY_OWNER_ID_AND_TAB)
    abstract fun getDataByIdAndTab(id: String, tab: String): LiveData<List<PortfolioModel>>

    /** Get paged item by id and tab */
    @Query(DbConstants.GET_PORTFOLIO_DATA_BY_OWNER_ID_AND_TAB)
    abstract fun getPagedDataByIdAndTab(id: String, tab: String): DataSource.Factory<Int, PortfolioModel>

    /** Get item by id */
    @Query(DbConstants.GET_PORTFOLIO_ITEM_BY_ID)
    abstract fun getItemById(id: String): LiveData<PortfolioModel>

//    /** Search by query */
//    @Query(DbConstants.GET_PORTFOLIO_BY_QUERY)
//    abstract fun getByQuery(query: String): LiveData<List<PortfolioModel>>

}