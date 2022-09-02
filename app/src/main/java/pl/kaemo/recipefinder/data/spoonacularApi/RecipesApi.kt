package pl.kaemo.recipefinder.data.spoonacularApi

import pl.kaemo.recipefinder.data.model.recipesList.RecipeDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesApi {

    @GET("/recipes/findByIngredients")

    suspend fun getRecipesResponse(
        @Query("apiKey") apiKey: String,
        @Query("ingredients") ingredients: String,
        @Query("number") number: Int,
        @Query("ranking") ranking: Int,
        @Query("ignorePantry") ignorePantry: Boolean
    ): Response<List<RecipeDTO>>

}