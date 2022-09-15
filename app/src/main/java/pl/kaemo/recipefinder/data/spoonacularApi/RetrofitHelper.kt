package pl.kaemo.recipefinder.data.spoonacularApi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor) // dodanie customowych logow do OkHttpClient
            .addInterceptor(quotaLeftInterceptor) // dodanie funkcji wyciagajacej punkty z hedera
            .callTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client) // inicjalizacja OkHttpClient
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipesApi::class.java)
    }
}