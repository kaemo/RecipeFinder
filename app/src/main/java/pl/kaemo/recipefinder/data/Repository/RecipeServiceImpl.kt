package pl.kaemo.recipefinder.data.Repository

import pl.kaemo.recipefinder.domain.Model.RecipePreview
import pl.kaemo.recipefinder.domain.RecipeService

class RecipeServiceImpl : RecipeService {
    override fun getRecipes(ingredients: List<String>): List<RecipePreview> {
        TODO("Here will be real implementation based on Spoonacular")
    }
}