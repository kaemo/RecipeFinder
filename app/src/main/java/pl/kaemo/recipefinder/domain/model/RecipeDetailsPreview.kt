package pl.kaemo.recipefinder.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeDetailsPreview(
    val id: Int,
    val title: String,
    val summary: String,
    val instructions: String,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceName: String?,
    val sourceUrl: String,
    val imageType: String,
    val extendedIngredientsAmount: List<Double>,
    val extendedIngredientsUnit: List<String>,
    val extendedIngredientsOriginalName: List<String>
) : Parcelable