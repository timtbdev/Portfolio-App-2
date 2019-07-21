package me.tumur.portfolio.screens.portfolio.pager

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import androidx.lifecycle.*
import androidx.paging.PagedList
import androidx.paging.toLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.tumur.portfolio.R
import me.tumur.portfolio.repository.database.model.portfolio.PortfolioModel
import me.tumur.portfolio.repository.network.Failed
import me.tumur.portfolio.repository.network.Success
import me.tumur.portfolio.repository.repo.Repository
import me.tumur.portfolio.utils.constants.Constants
import me.tumur.portfolio.utils.constants.DbConstants
import org.koin.core.KoinComponent
import org.koin.core.inject

class PortfolioPagerViewModel: ViewModel(), KoinComponent {

    /** VARIABLES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** Repository */
    private val repo: Repository by inject()

    /** Saved state */
    private val context: Context by inject()

    /** Tab based on view pager's position */
    private val _tab = MutableLiveData<String>()
    private val tab: LiveData<String> = _tab

    /** Selected item */
    private val _selectedItemId = MutableLiveData<String>()
    val selectedItemId: LiveData<String> = _selectedItemId

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

    val data: LiveData<PagedList<PortfolioModel>> = tab.switchMap { tab -> repo.getPortfolioPagedTabData(DbConstants.PERSON_ID, tab).toLiveData(config)}


    /** FUNCTIONS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /**
     * Set tab
     * */
    fun setTab(position: Int){
        when(position){
            0 -> _tab.value = Constants.ANDROID
            else -> _tab.value = Constants.WEB
        }
    }

    /**
     * Set selected item
     * */
    fun setSelectedItemId(id: String){
        _selectedItemId.value = id
    }

    /** Fetch network data and update the database */
    fun fetch() = viewModelScope.launch{
        when(withContext(Dispatchers.IO) { repo.fetchAll() }){
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
    private fun showToastMessage(message: String){
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast.setGravity(0, Gravity.CENTER_HORIZONTAL, Gravity.CENTER_VERTICAL)
        toast.show()
    }
    /** Set refresh status */
    fun setRefreshStatus(status: Boolean) {
        _isRefreshing.value = status
    }
}