package pl.kaemo.recipefinder.data

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import pl.kaemo.recipefinder.data.spoonacularApi.RecipesApi
import pl.kaemo.recipefinder.domain.model.RecipePreview
import pl.kaemo.recipefinder.domain.RecipeService

class RecipeServiceImpl(private val recipesApi: RecipesApi) : RecipeService {
    override suspend fun getRecipes(ingredients: List<String>): List<RecipePreview> {
        TODO("Here will be real implementation based on Spoonacular")


        // launching a new coroutine
//        GlobalScope.launch {
//            val result = recipesApi.getRecipes()
//            if (result != null)
//            // Checking the results
//                Log.d("nowy", result.body().toString())
//        }


    }
}