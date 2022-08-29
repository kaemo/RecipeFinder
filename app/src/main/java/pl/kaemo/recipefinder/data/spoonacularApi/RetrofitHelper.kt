package pl.kaemo.recipefinder.data.spoonacularApi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {

    private val baseUrl = "https://api.spoonacular.com/"

    private var instance: RecipesApi? = null

    fun getInstance(): RecipesApi {
        return instance ?: createInstance().also { instance = it } //sprawdzenie czy istnieje inna instancja
    }

    private fun createInstance(): RecipesApi {

        //customowe logi
        val interceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        val client = OkHttpClient.Builder().addInterceptor(interceptor)
            .callTimeout(10000L, TimeUnit.MILLISECONDS)
            .readTimeout(10000L, TimeUnit.MILLISECONDS)
            .writeTimeout(10000L, TimeUnit.MILLISECONDS)
            .build()

        return Retrofit.Builder().baseUrl(baseUrl)
            .client(client) //logi
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipesApi::class.java)
    }
}