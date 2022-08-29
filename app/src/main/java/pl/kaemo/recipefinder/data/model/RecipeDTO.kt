package pl.kaemo.recipefinder.data.model

import pl.kaemo.recipefinder.domain.model.RecipePreview

data class RecipeDTO(
//    val id: Int,
    val title: String,
    val image: String,
    val missedIngredients: List<MissedIngredientDTO>
//    val imageType: String,
//    val usedIngredientCount: Int,
//    val missedIngredientCount: Int,
//    val usedIngredients: List<UsedIngredientDTO>,
//    val unusedIngredients: List<UnusedIngredientDTO>,
//    val likes: Int

)

fun RecipeDTO.toRecipePreview(): RecipePreview {

    return RecipePreview(
        title = this.title,
        imageUrl = this.image,
        missedIngredients = this.missedIngredients.map { it.original }
    )
}