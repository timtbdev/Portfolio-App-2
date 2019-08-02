package me.tumur.portfolio.screens.welcome

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import me.tumur.portfolio.repository.database.dao.welcome.WelcomeDao
import me.tumur.portfolio.repository.database.model.welcome.WelcomeModel
import me.tumur.portfolio.utils.constants.Constants
import me.tumur.portfolio.utils.delegates.Preference
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * WelcomeViewModel designed to store and manage UI-related data in a lifecycle conscious way. This
 * allows data to survive configuration changes such as screen rotations. In addition, background
 * work such as fetching network results can continue through configuration changes and deliver
 * results after the new Fragment or Activity is available.
 */

class WelcomeViewModel: ViewModel(), KoinComponent{

    /** VARIABLES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** Repository */
    private val welcomeDao: WelcomeDao by inject()

    /** Shared preferences */
    private var isFirstRun by Preference(Constants.FIRST, true)

    /** Welcome data */
    val welcomeScreen = liveData(context = viewModelScope.coroutineContext + Dispatchers.IO){
        emitSource(welcomeDao.getListItems())
    }

    /** Current item of view pager  */
    private val _currentItem = MutableLiveData<Int>()
    val currentItem : LiveData<Int> = _currentItem

    /** ScrollTo item of view pager  */
    private val _scrollToItem = MutableLiveData<Int>()
    val scrollToItem : LiveData<Int> = _scrollToItem

    /** Skip and next button clicked  */
    private val _onClicked = MutableLiveData<Boolean>().apply { value = false }
    val onClicked : LiveData<Boolean> = _onClicked

    /** FUNCTIONS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /**
     * Set shared preferences
     * First run as {@Boolean as parameter}
     */
    fun setFirstRunAs(value: Boolean) {
        isFirstRun = value
    }

    /**
     * Get welcome screen data
     * */
    fun getWelcomeScreenData(position: Int): WelcomeModel?{
        return welcomeScreen.value?.get(position)
    }

    /**
     * Set viewpager's current item for icon animation
     */
    fun setCurrentItem(position: Int) {
        _currentItem.value = position
    }

    /**
     * Set viewpager's scroll to item
     */
    fun setScrollToItem(position: Int) {
        _scrollToItem.value = position
    }

    /**
     * Set viewpager's scroll to item
     */
    fun setOnClicked(status: Boolean) {
        _onClicked.apply { value = status }
    }
}
