package pl.kaemo.recipefinder.data.model

data class RecipeDTO(
    val id: Int,
    val title: String,
    val image: String,
    val imageType: String,
    val usedIngredientCount: Int,
    val missedIngredientCount: Int,
    val missedIngredients: List<MissedIngredientDTO>,
    val usedIngredients: List<UsedIngredientDTO>,
    val unusedIngredients: List<UnusedIngredientDTO>,
    val likes: Int
)
