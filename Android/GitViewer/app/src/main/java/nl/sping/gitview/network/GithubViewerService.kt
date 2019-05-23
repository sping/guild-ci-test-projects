package nl.sping.gitview.network

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import nl.sping.gitview.data.TokenWarehouse
import nl.sping.gitview.utils.Utils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by sebastiaan on 2019-05-15
 */
object GithubViewerService {
    const val BASE_URL = "https://api.github.com"

    fun createRetrofitService(context: Context): GithubApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(makeOkHttpClient(context))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(GithubApi::class.java)

    }

    private fun makeOkHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(makeBasicAuthenticationInterceptor(context))
            .addInterceptor(makeInternetCheckInterceptor(context))
            .addInterceptor(makeLoggingInterceptor())
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    private fun makeInternetCheckInterceptor(context: Context): Interceptor {
        return Interceptor { chain ->
            if (!Utils.isConnectedToInternet(context)) {
                throw NetworkUnavailableException()
            }

            chain.proceed(chain.request())
        }
    }

    private fun makeBasicAuthenticationInterceptor(context: Context): Interceptor {
        val token = TokenWarehouse.getAuthToken() ?: ""
        return Interceptor { chain ->
            var request = chain.request()
            val headers = request.headers().newBuilder()
                .add("Authorization", token)
                .build()

            request = request.newBuilder().headers(headers).build()
            chain.proceed(request)
        }
    }
}