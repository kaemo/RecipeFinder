package pl.kaemo.recipefinder.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import pl.kaemo.recipefinder.data.model.recipeDetails.ExtendedIngredientDTO

@Parcelize
data class RecipeDetailsPreview(
    val id: Int,
    val title: String,
    val summary: String,
    val instructions: String,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceName: String,
    val sourceUrl: String,
//    val extendedIngredients: List<ExtendedIngredientDTO>
) : Parcelable