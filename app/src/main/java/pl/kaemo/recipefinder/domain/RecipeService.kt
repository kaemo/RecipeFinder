package pl.kaemo.recipefinder.domain

import pl.kaemo.recipefinder.domain.model.RecipeDetailsPreview
import pl.kaemo.recipefinder.domain.model.RecipePreview
import pl.kaemo.recipefinder.domain.utils.Reply

interface RecipeService {
    suspend fun getRecipes(ingredients: List<String>): Reply<List<RecipePreview>>
    suspend fun getRecipeDetails(recipeId: Int): Reply<RecipeDetailsPreview>
}