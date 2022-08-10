package pl.kaemo.recipefinder.data.Model.SearchRecipesByIngredientsDTO

data class UsedIngredient(
    val aisle: String,
    val amount: Double,
    val id: Int,
    val image: String,
    val meta: List<String>,
    val name: String,
    val original: String,
    val originalName: String,
    val unit: String,
    val unitLong: String,
    val unitShort: String
)