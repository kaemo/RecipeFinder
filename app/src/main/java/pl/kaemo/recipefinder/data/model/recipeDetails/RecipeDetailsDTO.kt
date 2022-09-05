package pl.kaemo.recipefinder.data.model.recipeDetails

import pl.kaemo.recipefinder.domain.model.RecipeDetailsPreview

data class RecipeDetailsDTO(
    val id: Int,
    val title: String,
    val summary: String,
    val instructions: String,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceName: String?,
    val sourceUrl: String,
    val imageType: String,
    val extendedIngredients: List<ExtendedIngredientDTO>
    //    val cookingMinutes: Int,
    //    val preparationMinutes: Int,
    //    val winePairing: WinePairingDTO,
)

fun RecipeDetailsDTO.toRecipeDetailsPreview(): RecipeDetailsPreview {

    return RecipeDetailsPreview(
        id = this.id,
        title = this.title,
        summary = this.summary,
        instructions = this.instructions,
        readyInMinutes = this.readyInMinutes,
        servings = this.servings,
        sourceName = this.sourceName ?: ">> Click here to open the external site <<",
        sourceUrl = this.sourceUrl,
        imageType = this.imageType,
        extendedIngredientsAmount = this.extendedIngredients.map { it.amount },
        extendedIngredientsUnit = this.extendedIngredients.map { it.unit },
        extendedIngredientsOriginalName = this.extendedIngredients.map { it.originalName }
    )

}

//    val image: String,
//    val aggregateLikes: Int,
//    val analyzedInstructions: List<AnalyzedInstructionDTO>,
//    val cheap: Boolean,
//    val creditsText: String,
//    val cuisines: List<Any>,
//    val dairyFree: Boolean,
//    val diets: List<Any>,
//    val dishTypes: List<String>,
//    val gaps: String,
//    val glutenFree: Boolean,
//    val healthScore: Int,
//    val license: String,
//    val lowFodmap: Boolean,
//    val occasions: List<Any>,
//    val originalId: Any,
//    val pricePerServing: Double,
//    val spoonacularSourceUrl: String,
//    val sustainable: Boolean,
//    val vegan: Boolean,
//    val vegetarian: Boolean,
//    val veryHealthy: Boolean,
//    val veryPopular: Boolean,
//    val weightWatcherSmartPoints: Int,