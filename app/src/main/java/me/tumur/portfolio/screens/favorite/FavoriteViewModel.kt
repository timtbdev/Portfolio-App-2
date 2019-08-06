package me.tumur.portfolio.screens.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import androidx.paging.toLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.tumur.portfolio.repository.database.dao.favorite.FavoriteDao
import me.tumur.portfolio.repository.database.model.favorite.FavoriteModel
import me.tumur.portfolio.utils.state.Empty
import me.tumur.portfolio.utils.state.FavoriteState
import me.tumur.portfolio.utils.state.NotEmpty
import org.koin.core.KoinComponent
import org.koin.core.inject

class FavoriteViewModel : ViewModel(), KoinComponent {

    /** VARIABLES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** Repository */
    private val favoriteDao: FavoriteDao by inject()

    /** State */
    private val _state = MutableLiveData<FavoriteState>()
    val state: LiveData<FavoriteState> = _state

    /** Selected item id */
    private val _selectedItem = MutableLiveData<FavoriteModel>()
    val selectedItem: LiveData<FavoriteModel> = _selectedItem

    /** Delete item id */
    private val _deleteItemId = MutableLiveData<String>()
    val deleteItemId: LiveData<String> = _deleteItemId

    /** Favorite pager data */
    private val config = PagedList.Config.Builder()
        .setPageSize(10)
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(5)
        .build()

    val data: LiveData<PagedList<FavoriteModel>> = favoriteDao.getListItems().toLiveData(config)


    /** INITIALIZATIONS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    init {
        checkEmptyTable()
    }

    /** FUNCTIONS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /**
     * Set selected item
     * */
    fun setSelectedItem(item: FavoriteModel?, delete: Boolean) {
        item?.let {
            if (delete) _deleteItemId.value = it.id else _selectedItem.value = it
        }
    }

    /**
     * Set delete item id
     * */
    fun setDeleteItemId(id: String?) {
        id?.let {
            _deleteItemId.value = it
        }
    }

    /**
     * Set state
     * */
    private fun setState(state: FavoriteState) {
        _state.value = state
    }

    /**
     * Delete all
     * */
    fun deleteAllItems() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                favoriteDao.delete()
            }
        }
    }

    /**
     * Delete single item
     * */
    fun deleteSingleItem(id: String?) {
        id?.let {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    favoriteDao.deleteSingleItem(it)
                }
            }
        }
    }

    /**
     * Check for empty table
     * */
    fun checkEmptyTable() {
        viewModelScope.launch {
            val isEmpty = withContext(Dispatchers.IO) {
                favoriteDao.check()
            }
            if (isEmpty > 0) setState(NotEmpty) else setState(Empty)
        }
    }
}