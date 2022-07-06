package pl.kaemo.recipefinder.domain.UseCase

import pl.kaemo.recipefinder.domain.Model.RecipePreview
import pl.kaemo.recipefinder.domain.RecipeService

class GetRecipesForIngredientsUseCase(private val service: RecipeService) {
    fun execute(ingredients: List<String>): List<RecipePreview> {
        if (ingredients.isEmpty()) {
            throw Exception("Empty list is not allowed")
        }
        return service.getRecipes(ingredients)
    }
}