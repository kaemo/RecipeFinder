package pl.kaemo.recipefinder.data.spoonacularApi

import android.util.Log
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitHelper(val quotaHandlerSecond: (Float) -> Unit) {

    private val baseUrl = "https://api.spoonacular.com/"

    private var instance: RecipesApi? = null

    fun getInstance(): RecipesApi {
        return instance ?: createInstance().also {
            instance = it
        } //sprawdzenie czy istnieje inna instancja
    }

    private fun createInstance(): RecipesApi {
        //customowe logi
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        val quotaLeftInterceptor = QuotaLeftInterceptor(quotaHandlerSecond)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor) // dodanie customowych logow do OkHttpClient
            .addInterceptor(quotaLeftInterceptor) // dodanie funkcji wyciagajacej punkty z hedera
            .callTimeout(10000L, TimeUnit.MILLISECONDS)
            .readTimeout(10000L, TimeUnit.MILLISECONDS)
            .writeTimeout(10000L, TimeUnit.MILLISECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client) // inicjalizacja OkHttpClient
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipesApi::class.java)
    }
}

class QuotaLeftInterceptor(val quotaHandler: (Float) -> Unit) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val quota = getQuota(response.headers)
        quota?.let { quotaHandler(it) }
        return response
    }
}

private fun getQuota(header: Headers): Float? {
    val quotaLeft = header.firstOrNull { it.first == "x-api-quota-left" }?.second
    Log.d("quota left:", "$quotaLeft")
    return quotaLeft?.toFloat()
}