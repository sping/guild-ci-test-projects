package nl.sping.gitview.data

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by sebastiaan on 2019-05-15
 */
object TokenWarehouse {

    private lateinit var prefs: SharedPreferences

    private const val PREFS_NAME = "prefs"

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun setAuthToken(token: String) {
        prefs.edit().putString("TOKEN", token).apply()
    }

    fun getAuthToken(): String? {
        return prefs.getString("TOKEN", "")
    }

    fun clearTokens() {
        prefs.edit().remove("TOKEN").apply()
    }
}