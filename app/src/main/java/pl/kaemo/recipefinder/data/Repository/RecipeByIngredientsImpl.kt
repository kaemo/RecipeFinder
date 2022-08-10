package pl.kaemo.recipefinder.data.Repository

import SpoonacularAPI
import pl.kaemo.recipefinder.data.Model.SearchRecipesByIngredientsDTO.RecipeByIngredientsDTO
import pl.kaemo.recipefinder.domain.Repository.RecipeByIngredientsRepository
import javax.inject.Inject

class RecipeByIngredientsImpl @Inject constructor(
    private val api: SpoonacularAPI
) : RecipeByIngredientsRepository {
    override suspend fun getRecipesByIngredients(): List<RecipeByIngredientsDTO> {
        return api.getRecipesByIngredients()
    }
}