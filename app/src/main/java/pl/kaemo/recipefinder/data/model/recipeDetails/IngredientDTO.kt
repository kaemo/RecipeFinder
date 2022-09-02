package pl.kaemo.recipefinder.data.model.recipeDetails

data class IngredientDTO(
    val id: Int,
    val image: String,
    val localizedName: String,
    val name: String
)