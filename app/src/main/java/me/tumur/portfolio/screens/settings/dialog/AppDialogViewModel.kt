package me.tumur.portfolio.screens.settings.dialog

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import me.tumur.portfolio.repository.repo.Repository
import org.koin.core.KoinComponent
import org.koin.core.inject

class AppDialogViewModel: ViewModel(), KoinComponent{

    /** VARIABLES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** RepositoryImp */
    private val repo: Repository by inject()

    /** Profile data */
    val appInfo = liveData(context = viewModelScope.coroutineContext + Dispatchers.IO){
        emitSource(repo.getApp())
    }

    /** Close button on click */
    private val _closeButtonOnClick = MutableLiveData<Boolean>().apply { value = false }
    val closeButtonOnClick: LiveData<Boolean> = _closeButtonOnClick

    /** FUNCTIONS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /**
     * Set close button onClick event
     * */
    fun setCloseButtonOnClick(status: Boolean){
        _closeButtonOnClick.apply { value = status }
    }
}