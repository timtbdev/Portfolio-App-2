package me.tumur.portfolio.screens

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.tumur.portfolio.R
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

    /** Shared preferences */
    var isFirstRun by Preference(Constants.FIRST, true)

    /** Check network and cache conditions */
    private val network = (isNetworkAvailable(context))

    /** Screen state  */
    private val _screenState = MutableLiveData<ScreenState>().apply { value = SplashScreen }
    val screenState: LiveData<ScreenState> = _screenState

    /** Fragment state  */
    private val _fragmentState: MutableLiveData<String> =
        savedStateHandle.getLiveData(Constants.FRAGMENT_STATE, Constants.FRAGMENT_EMPTY)
    val fragmentState: LiveData<String> = _fragmentState

    /** Routed to saved Fragment state */
    private val _routed = MutableLiveData<Boolean>().apply { value = false }
    val routed: LiveData<Boolean> = _routed

    /** Navigation state  */
    private val _navigation =
        MutableLiveData<NavigationState>().apply { value = if (isFirstRun) HideNavigation else ShowNavigation }
    val navigation : LiveData<NavigationState> = _navigation

    /** Toast messages */
    private val msgFailed = context.getString(R.string.toast_failed)

    /** INITIALIZATION  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /**
     * init{} is called immediately when this ViewModel is created.
     */
    init {
        /** Check first run */
        if (isFirstRun) {
            val job = populateDb()
            job.isCompleted.let {
                if(network) fetch(WelcomeScreen) else     viewModelScope.launch {
                    setScreenStateWithDelay(WelcomeScreen)
                }
            }
        } else {

            when (fragmentState.value) {
                Constants.FRAGMENT_EMPTY -> {
                    viewModelScope.launch { setScreenStateWithDelay(LoaderScreen) }
                    if (network) fetch(MainScreen) else viewModelScope.launch { setScreenStateWithDelay(MainScreen) }
                }
                else -> setScreenState(MainScreen)
            }
        }
    }

    /** FUNCTIONS  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** Database population at very first run */
    private fun populateDb() = viewModelScope.launch{

        /** Fake dao is required to create and populate database from local resource  */
        withContext(Dispatchers.IO){ repo.checkWelcome() }
    }

    /** Fetch network data, update the database, set screen state */
    private fun fetch(screen: ScreenState?) = viewModelScope.launch{
        when(withContext(Dispatchers.IO) { repo.fetchAll() }){
            is Failed -> showToastMessage(msgFailed)
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

    /** Show toast message */
    private fun showToastMessage(message: String){
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast.setGravity(0, Gravity.CENTER_HORIZONTAL, Gravity.CENTER_VERTICAL)
        toast.show()
    }
}