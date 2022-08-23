package pl.kaemo.recipefinder.domain.model

data class RecipePreview(
    val title: String,
    val imageUrl: String?,
    val missedIngredients: List<String>
)
