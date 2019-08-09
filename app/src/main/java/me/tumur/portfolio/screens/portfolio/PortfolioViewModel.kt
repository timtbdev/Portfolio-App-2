package me.tumur.portfolio.screens.portfolio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import androidx.paging.toLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.tumur.portfolio.repository.database.dao.portfolio.PortfolioDao
import me.tumur.portfolio.repository.database.model.portfolio.PortfolioModel
import me.tumur.portfolio.repository.network.Failed
import me.tumur.portfolio.repository.network.Success
import me.tumur.portfolio.repository.repo.Repository
import me.tumur.portfolio.utils.constants.DbConstants
import me.tumur.portfolio.utils.state.ToastEmpty
import me.tumur.portfolio.utils.state.ToastShow
import me.tumur.portfolio.utils.state.ToastState
import org.koin.core.KoinComponent
import org.koin.core.inject

class PortfolioViewModel : ViewModel(), KoinComponent {

    /** VARIABLES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** Repository */
    private val dao: PortfolioDao by inject()
    private val repo: Repository by inject()

    /** Selected item id */
    private val _selectedItem = MutableLiveData<PortfolioModel>()
    val selectedItem: LiveData<PortfolioModel> = _selectedItem

    /** Pull to refresh status  */
    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    /** Show toast message from activity  */
    private val _showToast = MutableLiveData<ToastState>().apply { value = ToastEmpty }
    val showToast: LiveData<ToastState> = _showToast

    /** Portfolio pager data */
    private val config = PagedList.Config.Builder()
        .setPageSize(10)
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(5)
        .build()

    val data: LiveData<PagedList<PortfolioModel>> = dao.getListItems(DbConstants.PERSON_ID).toLiveData(config)

    /** FUNCTIONS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /**
     * Set selected item id and title
     * */
    fun setSelectedItem(item: PortfolioModel?) {
        _selectedItem.value = item
    }

    /** Fetch network data and update the database */
    fun fetch() = viewModelScope.launch {
        when (withContext(Dispatchers.IO) { repo.fetchAll() }) {
            is Failed -> {
                setShowToast(ToastShow)
                setRefreshStatus(false)
            }
            is Success -> {
                setRefreshStatus(false)
            }
        }
    }

    /** Set show toast message */
    fun setShowToast(state: ToastState) {
        _showToast.value = state
    }

    /** Set refresh status */
    fun setRefreshStatus(status: Boolean) {
        _isRefreshing.value = status
    }
}