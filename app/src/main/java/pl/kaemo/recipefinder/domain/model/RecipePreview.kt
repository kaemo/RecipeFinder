package pl.kaemo.recipefinder.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipePreview(
    val title: String,
    val imageUrl: String,
    val missedIngredients: List<String>?
) : Parcelable