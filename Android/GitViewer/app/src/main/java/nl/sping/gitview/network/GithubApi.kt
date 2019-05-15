package nl.sping.gitview.network

import com.google.gson.JsonObject
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by sebastiaan on 2019-05-15
 */
interface GithubApi {

    @GET("/users/repos")
    fun getRepos(): Deferred<Response<JsonObject>>
}