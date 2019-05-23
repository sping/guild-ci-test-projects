package nl.sping.gitview.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nl.sping.gitview.network.GithubApi
import nl.sping.gitview.network.NetworkUnavailableException
import retrofit2.HttpException

class GitRepository(private val api: GithubApi) {

    fun getAll(): LiveData<List<Git>> {
        val mutableLiveData = MutableLiveData<List<Git>>()

        CoroutineScope(Dispatchers.IO).launch {
            val request = api.getRepos()
            try {
                val response = request.await()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Log.d("LoginFragment", "Done")
                        val obj = response.body() ?: JsonObject()

                    } else {
                        Log.d("LoginFragment", "Error")
                    }
                }
            } catch (e: NetworkUnavailableException) {
                TokenWarehouse.clearTokens()
                Log.e("LoginFragment", "Error no interwebs")
            } catch (e: HttpException) {
                TokenWarehouse.clearTokens()
                Log.e("LoginFragment", "Exception ${e.message()}")
            } catch (e: Throwable) {
                TokenWarehouse.clearTokens()
                Log.e("LoginFragment", "Exception ${e.message}")
            }
        }

        return mutableLiveData
    }
}