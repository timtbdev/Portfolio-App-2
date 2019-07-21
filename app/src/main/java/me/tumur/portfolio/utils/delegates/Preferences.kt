package me.tumur.portfolio.utils.delegates

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import me.tumur.portfolio.R
import me.tumur.portfolio.utils.constants.Constants
import org.koin.core.KoinComponent
import org.koin.core.get
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Preference<T>(
    private val name: String,
    private val default: T
) : ReadWriteProperty<Any?, T>, KoinComponent {

    private val context = get() as Context


    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(Constants.APP, Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(name, default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(name, value)
    }

    private fun <T> findPreference(name: String, default: T): T = with(prefs) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException(context.resources.getString(R.string.error_shared_preference))
        }
        @Suppress("UNCHECKED_CAST")
        res as T
    }

    @SuppressLint("CommitPrefEdits")
    private fun <T> putPreference(name: String, value: T) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException(context.resources.getString(R.string.error_shared_preference))
        }.apply()
    }
}