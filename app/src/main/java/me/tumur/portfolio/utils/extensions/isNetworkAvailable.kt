package me.tumur.portfolio.utils.extensions

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.RequiresPermission

/**
 * Checks network access
 */

@RequiresPermission(value = Manifest.permission.ACCESS_NETWORK_STATE)
fun Any.isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context
        .getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    connectivityManager?.let {
        val netInfo = it.activeNetworkInfo
        netInfo?.let {network ->
            if(network.isConnected) return true
        }
    }
    return false
}