package me.tumur.portfolio.screens

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.screen_main.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.tumur.portfolio.R
import me.tumur.portfolio.databinding.ActivityMainBinding
import me.tumur.portfolio.utils.constants.Constants
import me.tumur.portfolio.utils.extensions.activityBinding
import me.tumur.portfolio.utils.state.HideNavigation
import me.tumur.portfolio.utils.state.ScreenState
import me.tumur.portfolio.utils.state.ShowNavigation
import me.tumur.portfolio.utils.state.WelcomeScreen


/**
 * An activity that inflates a layout that has [BottomNavigationView] and [NavigationView].
 * This is a single activity application that uses the Navigation library.
 * Content is displayed by Fragments.
 */
class MainActivity : AppCompatActivity() {

    /** VARIABLES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** ViewModel */

    /**
     * Returns a property delegate to access ViewModel
     * by default scoped to this Activity:
     * Default scope may be overridden with parameter ownerProducer:
     * This property can be accessed only after
     * this Activity is created i.e.,after Activity.onCreate,
     * and access prior to that will result in IllegalArgumentException.
     * */
    private val viewModel: MainViewModel by viewModels { SavedStateViewModelFactory(this) }

    /** Databinding */
    private val binding by activityBinding<ActivityMainBinding>(R.layout.activity_main)

    /** Navigation controller */
    private lateinit var navController: NavController

    /** Coroutine scope for delayed initialization */
    private val activityScope = CoroutineScope(Dispatchers.Default)

    /** Shared preference */
    private val prefs: SharedPreferences by lazy {
        this.getSharedPreferences(Constants.APP, Context.MODE_PRIVATE)
    }

    /** Shared preference listener */
    private var prefsListener: SharedPreferences.OnSharedPreferenceChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            when (key) {
                Constants.FIRST -> {
                    if(!prefs.getBoolean(Constants.FIRST, true) && viewModel.navigation.value == HideNavigation)
                        viewModel.setNavigationState(ShowNavigation)
                }
            }
        }

    /** INITIALIZATION * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /**
     * Called when the activity is starting.
     * This is where most initialization should go.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /** Binding lifecycle and viewmodel to layout */
        binding.apply {
            this.lifecycleOwner = this@MainActivity
            this.model = viewModel
        }

        /** Binding navigation controller with host fragment */
        navController = findNavController(R.id.main_screen_host_fragment)

        setSupportActionBar(binding.main.main_screen_toolbar)

        /** Delayed initialization */
        delayedInit()

        /** Set observers*/
        setWelcomeScreenObserver()
        setFragmentStateObserver()
    }

    /**
     * Called when the activity is resuming.
     * This is where it's starting to observe
     * the shared preference value change
     */
    override fun onResume() {
        super.onResume()
        prefs.registerOnSharedPreferenceChangeListener(prefsListener)
    }

    /**
     * Called when the activity is resuming.
     * This is where it's stopping to observe
     * the shared preference value change
     */
    override fun onPause() {
        super.onPause()
        prefs.unregisterOnSharedPreferenceChangeListener(prefsListener)
    }

    override fun onDestroy() {
        when(navController.currentDestination?.id){
            R.id.profile_nav -> viewModel.setFragmentState(Constants.FRAGMENT_PROFILE)
            R.id.portfolioScreen -> viewModel.setFragmentState(Constants.FRAGMENT_PORTFOLIO)
            R.id.experienceScreen -> viewModel.setFragmentState(Constants.FRAGMENT_EXPERIENCE)
            R.id.settingsScreen -> viewModel.setFragmentState(Constants.FRAGMENT_SETTINGS)
        }
        super.onDestroy()
    }

    /** FUNCTIONS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /** Set delayed initialization */
    private fun delayedInit(){
        activityScope.launch {
            setBottomMenu()
            setSideMenu()
            //setNavigationListener()
        }
    }

    /** Setup welcome screen observer  */
    private fun setWelcomeScreenObserver(){
        val screenWelcomeObserver = Observer<ScreenState> { state ->
            when(state){
                is WelcomeScreen ->navController.navigate(R.id.action_global_welcomeScreen)
            }
        }
        viewModel.screenState.observe(this, screenWelcomeObserver)
    }

    /** Setup fragment state observer  */
    private fun setFragmentStateObserver(){
        val fragmentStateObserver = Observer<String> { state ->
            if(viewModel.routed.value == false){
                when(state){
                    Constants.FRAGMENT_PROFILE ->navController.navigate(R.id.profile_nav)
                    Constants.FRAGMENT_PORTFOLIO ->navController.navigate(R.id.portfolioScreen)
                    Constants.FRAGMENT_EXPERIENCE ->navController.navigate(R.id.experienceScreen)
                }
                viewModel.setRouted(true)
            }
        }
        viewModel.fragmentState.observe(this, fragmentStateObserver)
    }

//    /** Navigation destination change listener for saved state handle -> Last opened Fragment */
//    private fun setNavigationListener(){
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when(destination.id){
//                R.id.profile_nav -> viewModel.setFragmentState(Constants.FRAGMENT_PROFILE)
//                R.id.portfolioScreen -> viewModel.setFragmentState(Constants.FRAGMENT_PORTFOLIO)
//                R.id.experienceScreen -> viewModel.setFragmentState(Constants.FRAGMENT_EXPERIENCE)
//                R.id.settingsScreen -> viewModel.setFragmentState(Constants.FRAGMENT_SETTINGS)
//            }
//        }
//    }

    /** Setup bottom menu */
    private fun setBottomMenu(){
        // Variables
        val navigationGraphTopLevel = setOf(R.id.profileScreen, R.id.portfolioScreen, R.id.experienceScreen, R.id.settingsScreen)
        // Application bar configuration for navigationController
        val appBarConfiguration = AppBarConfiguration(navigationGraphTopLevel)
        // Setup bottom menu
        binding.main.main_screen_bottom_menu?.apply {
            // Show the true colors of menu icons
            this.itemIconTintList = null
            // Setup navController with bottom menu and toolbar
            this.setupWithNavController(navController)
        }
        binding.main.main_screen_toolbar?.setupWithNavController(navController, appBarConfiguration)
    }

    /** Setup side menu */
    private fun setSideMenu(){
        // Variables
        val navigationGraphTopLevel = setOf(R.id.profileScreen, R.id.portfolioScreen, R.id.experienceScreen, R.id.settingsScreen)
        val drawerLayout = findViewById<DrawerLayout>(R.id.main_screen_drawer_layout)
        // Application bar configuration for navigationController
        val appBarConfiguration = AppBarConfiguration(navigationGraphTopLevel, drawerLayout)
        // Setup side menu
        binding.main.main_screen_side_menu?.apply {
            // Show the true colors of menu icons
            this.itemIconTintList = null
            // Setup navController with side menu with drawerlayout and toolbar
            this.setupWithNavController(navController)
        }
        binding.main.main_screen_toolbar?.setupWithNavController(navController, appBarConfiguration)
    }
}
