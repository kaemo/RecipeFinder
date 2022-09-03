package pl.kaemo.recipefinder.domain

import pl.kaemo.recipefinder.domain.model.RecipeDetailsPreview
import pl.kaemo.recipefinder.domain.model.RecipePreview

interface RecipeService {
    suspend fun getRecipes(ingredients: List<String>): List<RecipePreview>
    suspend fun getRecipeDetails(recipeId: Int): RecipeDetailsPreview
}