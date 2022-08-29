package pl.kaemo.recipefinder.data

import android.util.Log
import pl.kaemo.recipefinder.data.model.toRecipePreview
import pl.kaemo.recipefinder.data.spoonacularApi.RecipesApi
import pl.kaemo.recipefinder.domain.RecipeService
import pl.kaemo.recipefinder.domain.model.RecipePreview

// API docs: spoonacular.com/food-api/docs#Search-Recipes-by-Ingredients

const val apiKey: String = "df1ab77d91fe41a4b8b0012f3fc62dab"
const val number: Int = 20 //The maximum number of recipes to return (between 1 and 100). Defaults to 10
const val ranking: Int = 2 //Whether to maximize used ingredients (1) or minimize missing ingredients (2) first
const val ignorePantry: Boolean = true //Whether to ignore typical pantry items, such as water, salt, flour, etc.

class RecipeServiceImpl(private val recipesApi: RecipesApi) : RecipeService {
    override suspend fun getRecipes(ingredients: List<String>): List<RecipePreview> {

        val result = recipesApi.getRecipes(
            apiKey,
            formatToApiString(ingredients),
            number,
            ranking,
            ignorePantry
        )

        getQuotaLeftFromHeader(result.headers().toString()) // lepszy sposób na wyciągnięcie x-api-quota-left: <tej wartości> ? / jak to najlepiej przesłać do adaptera na kolejnym ekranie?

        return result.body()?.map {
            it.toRecipePreview()
        } ?: emptyList()

    }

    private fun getQuotaLeftFromHeader(header: String) {
        val afterQuotaIndex: Int = header.indexOf("cf-cache-status") - 1
        var quotaLeft: String = header.dropLast(header.length - afterQuotaIndex)
        quotaLeft = quotaLeft.drop(quotaLeft.indexOf("x-api-quota-left") + 18)
        Log.d("quota left:", quotaLeft)
    }

    private fun formatToApiString(ingredientsList: List<String>): String {
        var formattedIngredients = ""
        ingredientsList.forEach { formattedIngredients += "$it," }
        return formattedIngredients.dropLast(1)
    }
}

