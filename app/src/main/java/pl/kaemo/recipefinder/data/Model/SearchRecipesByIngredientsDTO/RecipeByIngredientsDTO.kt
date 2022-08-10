package pl.kaemo.recipefinder.data.Model.SearchRecipesByIngredientsDTO

import pl.kaemo.recipefinder.domain.Model.RecipeByIngredients

data class RecipeByIngredientsDTO(
    val id: Int,
    val image: String,
    val imageType: String,
    val likes: Int,
    val missedIngredientCount: Int,
    val missedIngredients: List<MissedIngredient>,
    val title: String,
    val unusedIngredients: List<Any>,
    val usedIngredientCount: Int,
    val usedIngredients: List<UsedIngredient>
)

fun RecipeByIngredientsDTO.toRecipeByIngredients(): RecipeByIngredients {
    return RecipeByIngredients(
        id = id,
        image = image,
        missedIngredientCount = missedIngredientCount,
        title = title,
        usedIngredientCount = usedIngredientCount
    )
}