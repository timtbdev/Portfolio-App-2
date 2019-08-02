package me.tumur.portfolio.screens.portfolio

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import androidx.paging.toLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.tumur.portfolio.R
import me.tumur.portfolio.repository.database.dao.portfolio.PortfolioDao
import me.tumur.portfolio.repository.database.model.portfolio.PortfolioModel
import me.tumur.portfolio.repository.network.Failed
import me.tumur.portfolio.repository.network.Success
import me.tumur.portfolio.repository.repo.Repository
import me.tumur.portfolio.utils.constants.DbConstants
import org.koin.core.KoinComponent
import org.koin.core.inject

class PortfolioViewModel : ViewModel(), KoinComponent {

    /** VARIABLES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** Repository */
    private val dao: PortfolioDao by inject()
    private val repo: Repository by inject()

    /** Saved state */
    private val context: Context by inject()

    /** Selected item id */
    private val _selectedItem = MutableLiveData<PortfolioModel>()
    val selectedItem: LiveData<PortfolioModel> = _selectedItem

    /** Pull to refresh status  */
    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    /** Toast messages */
    private val msgFailed = context.getString(R.string.toast_failed)

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
                showToastMessage(msgFailed)
                setRefreshStatus(false)
            }
            is Success -> {
                setRefreshStatus(false)
            }
        }
    }

    /** Show toast message */
    private fun showToastMessage(message: String) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast.setGravity(0, Gravity.CENTER_HORIZONTAL, Gravity.CENTER_VERTICAL)
        toast.show()
    }

    /** Set refresh status */
    fun setRefreshStatus(status: Boolean) {
        _isRefreshing.value = status
    }
}