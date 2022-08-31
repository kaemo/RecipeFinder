package pl.kaemo.recipefinder.domain

import pl.kaemo.recipefinder.domain.model.RecipePreview

interface RecipeService {
    suspend fun getRecipes(ingredients: List<String>): List<RecipePreview>
}