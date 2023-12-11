package ir.dorsa.news_task.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build


fun Context.isConnectionOn(): Boolean {
    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as
                ConnectivityManager

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        postAndroidMInternetCheck(connectivityManager)
    } else {
        preAndroidMInternetCheck(connectivityManager)
    }
}



private fun postAndroidMInternetCheck(
    connectivityManager: ConnectivityManager
): Boolean {
    val network = connectivityManager.activeNetwork
    val connection =
        connectivityManager.getNetworkCapabilities(network)

    return connection != null && (
            connection.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    connection.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
}



private fun preAndroidMInternetCheck(
    connectivityManager: ConnectivityManager
): Boolean {
    val activeNetwork = connectivityManager.activeNetworkInfo
    if (activeNetwork != null) {
        return (activeNetwork.type == ConnectivityManager.TYPE_WIFI ||
                activeNetwork.type == ConnectivityManager.TYPE_MOBILE)
    }

    return false
}