package pl.kaemo.recipefinder.data

import pl.kaemo.recipefinder.domain.model.RecipePreview
import pl.kaemo.recipefinder.domain.RecipeService

class RecipeServiceImpl : RecipeService {
    override suspend fun getRecipes(ingredients: List<String>): List<RecipePreview> {
        TODO("Here will be real implementation based on Spoonacular")
    }
}