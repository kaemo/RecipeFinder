package pl.kaemo.recipefinder.data.spoonacularApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private val baseUrl = "https://api.spoonacular.com/"

    private var instance: RecipesApi? = null

    fun getInstance(): RecipesApi {
        return instance ?: createInstance().also { instance = it }
    }

    private fun createInstance(): RecipesApi {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipesApi::class.java)
    }
}