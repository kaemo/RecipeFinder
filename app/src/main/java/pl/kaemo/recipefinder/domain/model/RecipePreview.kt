package pl.kaemo.recipefinder.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipePreview(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val missedIngredients: List<String>?
//    val imageType: String,
//    val usedIngredientCount: Int,
//    val missedIngredientCount: Int,
//    val usedIngredients: List<UsedIngredientDTO>,
//    val unusedIngredients: List<UnusedIngredientDTO>,
//    val likes: Int
) : Parcelable