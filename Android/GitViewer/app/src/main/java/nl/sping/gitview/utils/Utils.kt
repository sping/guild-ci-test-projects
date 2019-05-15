package nl.sping.gitview.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by sebastiaan on 2019-05-15
 */
object Utils {

    fun isConnectedToInternet(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
    }
}