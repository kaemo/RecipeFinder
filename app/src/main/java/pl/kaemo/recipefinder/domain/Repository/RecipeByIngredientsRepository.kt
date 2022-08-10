package pl.kaemo.recipefinder.domain.Repository

import pl.kaemo.recipefinder.data.Model.SearchRecipesByIngredientsDTO.RecipeByIngredientsDTO

interface RecipeByIngredientsRepository {
    suspend fun getRecipesByIngredients(): List<RecipeByIngredientsDTO>
}