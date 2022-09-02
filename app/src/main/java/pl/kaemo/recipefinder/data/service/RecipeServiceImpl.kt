package pl.kaemo.recipefinder.data.service

import pl.kaemo.recipefinder.data.model.recipeDetails.toRecipeDetailsPreview
import pl.kaemo.recipefinder.data.model.recipesList.toRecipePreview
import pl.kaemo.recipefinder.data.spoonacularApi.RecipesApi
import pl.kaemo.recipefinder.domain.RecipeService
import pl.kaemo.recipefinder.domain.model.RecipeDetailsPreview
import pl.kaemo.recipefinder.domain.model.RecipePreview
import javax.inject.Inject

// API docs: spoonacular.com/food-api/docs#Search-Recipes-by-Ingredients

const val apiKey: String = "df1ab77d91fe41a4b8b0012f3fc62dab"
const val number: Int = 20 //The maximum number of recipes to return (between 1 and 100). Defaults to 10
const val ranking: Int = 2 //Whether to maximize used ingredients (1) or minimize missing ingredients (2) first
const val ignorePantry: Boolean = true //Whether to ignore typical pantry items, such as water, salt, flour, etc.

const val includeNutrition: Boolean = false //Include nutrition data in the recipe information. Nutrition data is per serving. If you want the nutrition data for the entire recipe, just multiply by the number of servings.

class RecipeServiceImpl @Inject constructor(private val recipesApi: RecipesApi) : RecipeService {
    override suspend fun getRecipes(ingredients: List<String>): List<RecipePreview> {

        val result = recipesApi.getRecipesResponse(
            apiKey,
            formatToApiString(ingredients),
            number,
            ranking,
            ignorePantry
        )

        return result.body()?.map {
            it.toRecipePreview()
        } ?: emptyList()

    }

    override suspend fun getRecipeDetails(recipeId: Int): List<RecipeDetailsPreview> {

//        val result = recipesApi.getRecipesDetailsResponse(
//            recipeId,
//            apiKey,
//            includeNutrition
//        )
//
//        return result.body()?.map {
//            it.toRecipeDetailsPreview()
//        } ?: emptyList()

        return emptyList()

    }

    private fun formatToApiString(ingredientsList: List<String>): String {
        var formattedIngredients = ""
        ingredientsList.forEach { formattedIngredients += "$it," }
        return formattedIngredients.dropLast(1)
    }
}

