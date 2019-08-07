package me.tumur.portfolio.screens

import android.content.Context
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.tumur.portfolio.repository.database.dao.welcome.WelcomeDao
import me.tumur.portfolio.repository.network.Failed
import me.tumur.portfolio.repository.repo.Repository
import me.tumur.portfolio.utils.constants.Constants
import me.tumur.portfolio.utils.delegates.Preference
import me.tumur.portfolio.utils.extensions.isNetworkAvailable
import me.tumur.portfolio.utils.state.*
import org.koin.core.KoinComponent
import org.koin.core.inject


/**
 * MainViewModel designed to store and manage UI-related data in a lifecycle conscious way. This
 * allows data to survive configuration changes such as screen rotations. In addition, background
 * work such as fetching network results can continue through configuration changes and deliver
 * results after the new Fragment or Activity is available.
 */
class MainViewModel(state : SavedStateHandle): ViewModel(), KoinComponent {

    /** VARIABLES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** Saved state */
    private val context: Context by inject()

    /** Saved state */
    private val savedStateHandle = state

    /** RepositoryImp */
    private val repo: Repository by inject()
    private val welcomeDao: WelcomeDao by inject()

    /** Shared preferences */
    private var isFirstRun by Preference(Constants.FIRST, true)

    /** Check network and cache conditions */
    private val network = (isNetworkAvailable(context))

    /** Screen state  */
    private val _screenState = MutableLiveData<ScreenState>()
    val screenState: LiveData<ScreenState> = _screenState

    /** Fragment state  */
    private val _fragmentState = MutableLiveData<String>()
    val fragmentState: LiveData<String> = _fragmentState

    /** Routed to saved Fragment state */
    private val _routed = MutableLiveData<Boolean>().apply { value = false }
    val routed: LiveData<Boolean> = _routed

    /** Navigation state  */
    private val _navigation =
        MutableLiveData<NavigationState>().apply { value = if (isFirstRun) HideNavigation else ShowNavigation }
    val navigation : LiveData<NavigationState> = _navigation

    /** Show a toast message */
    private val _showToast = MutableLiveData<ToastState>().apply { value = ToastEmpty }
    val showToast: LiveData<ToastState> = _showToast

    /** INITIALIZATION  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /**
     * init{} is called immediately when this ViewModel is created.
     */
    init {
        /** Check first run */
        if (isFirstRun) {
            setScreenState(SplashScreen)
            val job = populateDb()
            job.isCompleted.let {
                if (network) fetch(WelcomeScreen)
                else viewModelScope.launch { setScreenStateWithDelay(WelcomeScreen) }
            }
        } else {
            when (getSavedStateHandle()) {
                Constants.FRAGMENT_EMPTY -> {
                    setScreenState(SplashScreen)
                    if (network) fetch(MainScreen) else viewModelScope.launch { setScreenStateWithDelay(MainScreen) }
                }
                else -> setScreenState(MainScreen)
            }
        }
    }

    override fun onCleared() {
        setSavedStateHandle(fragmentState.value)
        super.onCleared()
    }

    /** FUNCTIONS  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


    /** Set saved state handle */
    private fun setSavedStateHandle(state: String?) {
        // Sets a new value for the object associated to the key.
        state?.let {
            savedStateHandle.set(Constants.FRAGMENT_STATE, it)
        }

    }

    /** Get saved state handle */
    private fun getSavedStateHandle(): String {
        // Gets the current value of the user id from the saved state handle
        return savedStateHandle.get(Constants.FRAGMENT_STATE) ?: Constants.FRAGMENT_EMPTY
    }

    /** Database population at very first run */
    private fun populateDb() = viewModelScope.launch{

        /** Fake dao is required to create and populate database from local resource  */
        withContext(Dispatchers.IO) { welcomeDao.check() }
    }

    /** Fetch network data, update the database, set screen state */
    private fun fetch(screen: ScreenState?) = viewModelScope.launch{
        when(withContext(Dispatchers.IO) { repo.fetchAll() }){
            is Failed -> setShowToast(ToastShow)
        }
        screen?.let { setScreenStateWithDelay(screen) }
    }

    /** Set navigation state */
    fun setNavigationState(state: NavigationState){
        _navigation.value = state
    }

    /** Set saved state handle for screen state */
    private fun setScreenState(state: ScreenState) {
        _screenState.value = state
    }

    /** Set saved state handle for screen state */
    private suspend fun setScreenStateWithDelay(state: ScreenState) {
        delay(1000L)
        _screenState.value = state
    }

    /** Set saved state handle for fragment state */
    fun setFragmentState(state: String) {
        // Sets a new value for the object associated to the key.
        if (state != fragmentState.value) _fragmentState.value = state
    }

    /** Set routed fragment state for saved state handle */
    fun setRouted(state: Boolean) {
        // Sets a new value for the object associated to the key.
        _routed.value = state
    }

    /**
     * Set show toast
     * */
    fun setShowToast(state: ToastState) {
        _showToast.value = state
    }
}