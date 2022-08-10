package pl.kaemo.recipefinder.UI.MainActivity.recipe_list

import pl.kaemo.recipefinder.domain.Model.RecipeByIngredients

data class RecipeListState(
    val isLoading: Boolean = false,
    val recipesByIngredients: List<RecipeByIngredients> = emptyList(),
    val error: String = ""
)
