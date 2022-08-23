package pl.kaemo.recipefinder.data.model

data class RecipeDTO(
    val id: String,
    val title: String,
    val image: String, //todo find description source in api
)
