package me.tumur.portfolio.screens.portfolio.detail.preview

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import me.tumur.portfolio.repository.database.dao.screenshot.ScreenShotDao
import me.tumur.portfolio.repository.database.model.screenshot.ScreenShotModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class PreviewFragmentViewModel : ViewModel(), KoinComponent {

    /** VARIABLES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** Repository */
    private val screenShotDao: ScreenShotDao by inject()

    /** Id */
    private val _id = MutableLiveData<String>()
    val id: LiveData<String> = _id

    /** Current item of view pager  */
    private val _currentItem = MutableLiveData<Int>()
    val currentItem: LiveData<Int> = _currentItem

    /** Screenshots */
    val data = id.switchMap {
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            emitSource(screenShotDao.getListItems(it))
        }
    }

    /** ScrollTo item of view pager  */
    private val _scrollToItem = MutableLiveData<Int>()
    val scrollToItem: LiveData<Int> = _scrollToItem

    /** FUNCTIONS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /**
     * Set id
     * */
    fun setId(id: String?) {
        id?.let {
            _id.value = it
        }
    }

    /**
     * Set viewpager's current item for icon animation
     */
    fun setCurrentItem(position: Int) {
        _currentItem.value = position
    }

    /**
     * Get a screenshot data
     * */
    fun getScreenShot(position: Int): ScreenShotModel? {
        return data.value?.get(position)
    }

    /**
     * Set viewpager's scroll to item
     */
    fun setScrollToItem(position: Int) {
        _scrollToItem.value = position
    }
}