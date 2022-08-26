package pl.kaemo.recipefinder.data.spoonacularApi

import pl.kaemo.recipefinder.data.model.RecipeDTO
import retrofit2.Response
import retrofit2.http.GET

interface RecipesApi {
    @GET("/recipes/findByIngredients?apiKey=df1ab77d91fe41a4b8b0012f3fc62dab&ingredients=chicken,potatoes,cheese,flour,sugar,rosemary,tomatoes&number=10&ranking=1")
    suspend fun getRecipes(): Response<RecipeDTO>
}