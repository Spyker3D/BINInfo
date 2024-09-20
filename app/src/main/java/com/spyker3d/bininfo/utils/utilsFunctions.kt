package com.spyker3d.bininfo.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.spyker3d.bininfo.R

fun isConnected(context: Context): Boolean {
    val connectivityManager = context.getSystemService(
        Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager
    val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
    return false

}

fun Fragment.showSnackBar(message: String) {
    Snackbar
        .make(requireView(), message, Snackbar.LENGTH_SHORT)
        .setBackgroundTint(resources.getColor(R.color.snack_bar_color))
        .show()

}