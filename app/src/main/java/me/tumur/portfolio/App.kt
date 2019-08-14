package me.tumur.portfolio

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.work.*
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.tumur.portfolio.repository.network.DbRefresh
import me.tumur.portfolio.utils.constants.Constants
import me.tumur.portfolio.utils.koin.appModule
import me.tumur.portfolio.utils.theme.ThemeHelper
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber
import java.util.concurrent.TimeUnit

class App : Application(), Configuration.Provider {

    /**
     * onCreate is called before the first screen is shown to the user.
     *
     * Use it to setup any background tasks, running expensive setup operations in a background
     * thread to avoid delaying app start.
     */

    /** Coroutine scope to delayed initialization*/
    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()

        /**
         * KOIN
         * */
        startKoin {
            // AndroidLogger as Koin Logger - default Level.INFO
            androidLogger()
            // Android context
            androidContext(this@App)
            // Load properties from assets/koin.properties file
            androidFileProperties()
            /** Koin's module list */
            modules(appModule)
        }

        /**
         * THREETENABP
         * */
        AndroidThreeTen.init(this)

        /**
         * STETHO
         * */
        Stetho.initializeWithDefaults(this)

        /**
         * TIMBER
         * */

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        /**
         * THEME SETTINGS
         * */
        /** Shared preferences */
        val sharedPref: SharedPreferences = getSharedPreferences(Constants.APP, Context.MODE_PRIVATE)
        val theme =
            sharedPref.getString(resources.getString(R.string.preference_key_theme_option), Constants.THEME_DEFAULT)
        theme?.let {
            ThemeHelper.applyTheme(it)
        }

        /**
         * AUTO BACKGROUND PERIODIC SYNC
         * */
        /** Shared preferences */
        if(sharedPref.getBoolean(resources.getString(R.string.preference_key_bg_sync), true)) setDelayedBackgroundSync()

    }

    /**
     * WorkManager
     * On-demand initialization
     * */
    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .build()
    }

    private fun setDelayedBackgroundSync() {
        applicationScope.launch {
            setupBackgroundSyncWork()
        }
    }

    private fun setupBackgroundSyncWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setRequiresDeviceIdle(true)
                }
            }.build()

        val repeatingRequest
                = PeriodicWorkRequestBuilder<DbRefresh>(12, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            resources.getString(R.string.preference_key_bg_sync),
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest)
    }
}