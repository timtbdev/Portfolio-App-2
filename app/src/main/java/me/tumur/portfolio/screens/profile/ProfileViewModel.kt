package me.tumur.portfolio.screens.profile

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import me.tumur.portfolio.repository.database.dao.profile.AboutDao
import me.tumur.portfolio.repository.database.dao.profile.ProfileDao
import me.tumur.portfolio.repository.database.dao.profile.SocialDao
import me.tumur.portfolio.repository.database.model.profile.SocialModel
import me.tumur.portfolio.utils.constants.DbConstants
import org.koin.core.KoinComponent
import org.koin.core.inject

class ProfileViewModel: ViewModel(), KoinComponent{

    /** VARIABLES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** Repository */
    private val profileDao: ProfileDao by inject()
    private val aboutDao: AboutDao by inject()
    private val socialDao: SocialDao by inject()

    /** Profile */
    val profile = liveData(context = viewModelScope.coroutineContext + Dispatchers.IO){
        emitSource(profileDao.getSingleItem(DbConstants.PERSON_ID))
    }

    /** About */
    val about = liveData(context = viewModelScope.coroutineContext + Dispatchers.IO){
        emitSource(aboutDao.getListItems(DbConstants.PERSON_ID))
    }

    /** Social*/
    val social = liveData(context = viewModelScope.coroutineContext + Dispatchers.IO){
        emitSource(socialDao.getListItems(DbConstants.PERSON_ID))
    }


    /** Profile bottom sheet dialog */
    private val _showProfileBottomSheet = MutableLiveData<Boolean>().apply { value = false }
    val showProfileBottomSheet: LiveData<Boolean> = _showProfileBottomSheet

    /** Social item on click */
    private val _socialItemOnClick = MutableLiveData<SocialModel>()
    val socialItemOnClick: LiveData<SocialModel> = _socialItemOnClick

    /** Email item on click */
    private val _emailItemOnClick = MutableLiveData<Boolean>().apply { value = false }
    val emailItemOnClick: LiveData<Boolean> = _emailItemOnClick

    /** FUNCTIONS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /**
     * Set show profile bottom sheet
     * */
    fun setShowProfileBottomsheet(status: Boolean){
        _showProfileBottomSheet.apply { value = status }
    }
    // Set back to @false after it showed
    fun resetShowProfileBottomsheet(){
        _showProfileBottomSheet.apply { value = false }
    }

    /**
     * Set social item onClick event
     * */
    fun socialItemOnClick(item: SocialModel){
        _socialItemOnClick.apply { value = item }
    }
    // Set back to @null after it navigated
    fun socialItemClicked(){
        _socialItemOnClick.apply { value = null }
    }

    /**
     * Set email item onClick event
     * */
    fun emailItemOnClick(status: Boolean){
        _emailItemOnClick.apply { value = status }
    }
    // Set back to @null after it navigated
    fun emailItemClicked(){
        _emailItemOnClick.apply { value = false }
    }
}