package pl.kaemo.recipefinder.data.model

data class UnusedIngredientDTO(
    val id: Int,
    val amount: Int,
    val unit: String,
    val unitLong: String,
    val unitShort: String,
    val aisle: String,
    val name: String,
    val original: String,
    val originalName: String,
    val meta: List<Any>,
    val image: String
)
