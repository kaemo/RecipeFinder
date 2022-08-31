package pl.kaemo.recipefinder.data.spoonacularApi

import android.content.Context
import android.util.Log
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import pl.kaemo.recipefinder.ui.util.SHARED_PREFS_KEY
import pl.kaemo.recipefinder.ui.util.SHARED_PREF_QUOTALEFT_KEY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RetrofitHelper @Inject constructor(val quotaLeftInterceptor: QuotaLeftInterceptor) {

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
//        val quotaLeftInterceptor = QuotaLeftInterceptor(quotaHandlerSecond)

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

// do osobnego pliku wyrzucić
class QuotaLeftInterceptor @Inject constructor(val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val quota = getQuota(response.headers)
        val sharedPrefs = context.getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE)
        quota?.let {
            sharedPrefs.edit().putFloat(SHARED_PREF_QUOTALEFT_KEY, it).apply()
        }
        return response
    }
}

private fun getQuota(header: Headers): Float? {
    val quotaLeft = header.firstOrNull { it.first == "x-api-quota-left" }?.second
    Log.d("quota left:", "$quotaLeft")
    return quotaLeft?.toFloat()
}