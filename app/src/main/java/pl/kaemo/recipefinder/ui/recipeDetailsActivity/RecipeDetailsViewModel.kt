package pl.kaemo.recipefinder.ui.recipeDetailsActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.kaemo.recipefinder.domain.model.RecipeDetailsPreview
import pl.kaemo.recipefinder.ui.util.CustomLogger
import pl.kaemo.recipefinder.ui.util.LogcatLogger
import pl.kaemo.recipefinder.ui.util.UiMessage
import pl.kaemo.recipefinder.ui.util.trimIfMoreDecimalThan
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    savedState: SavedStateHandle
) : ViewModel() {

    val logger: CustomLogger = LogcatLogger("RecipeDetailsVM") // lub FileLogger()

    val extraRecipeDetails: RecipeDetailsPreview? =
        savedState.get<RecipeDetailsPreview>("extraRecipeId")

    private val _ingredientsList = MutableLiveData<String>("loading...")
    val ingredientsList: LiveData<String> = _ingredientsList

    private val _uiMessages = MutableLiveData<UiMessage>()
    val uiMessages: LiveData<UiMessage> = _uiMessages

    fun onAddToWishlistClicked() {
        val toast = UiMessage.Toast("Wishlist not implemented yet!")
        _uiMessages.postValue(toast)
    }

    fun onTextViewTimeClicked() {
        val toast = UiMessage.Toast("Detailed preview not implemented yet!")
        _uiMessages.postValue(toast)
    }

    fun onServingsClicked() {
        val toast = UiMessage.Toast("Variable servings not implemented yet!")
        _uiMessages.postValue(toast)
    }

    fun onMetricSwitchClicked() {
        val toast = UiMessage.Toast("Metric switch not implemented yet!")
        _uiMessages.postValue(toast)
    }

    fun onTextViewSourceLinkClicked() {
        val toast = UiMessage.Toast("Source link not implemented yet!")
        _uiMessages.postValue(toast)
    }

    fun onNutritionalServingsClicked() {
        val toast = UiMessage.Toast("Variable servings not implemented yet!")
        _uiMessages.postValue(toast)
    }

    fun onSimilarRecipesButtonClicked() {
        val toast = UiMessage.Toast("Similar recipes list not implemented yet!")
        _uiMessages.postValue(toast)
    }

    init {
        var ingredientsListString = ""
        extraRecipeDetails?.extendedIngredientsAmount?.forEachIndexed { index, it ->
            ingredientsListString += "• ${
                it.trimIfMoreDecimalThan(2).toString().trimEnd { it == '0' }.trimEnd { it == '.' }
            } ${extraRecipeDetails.extendedIngredientsUnit[index]} ${extraRecipeDetails.extendedIngredientsOriginalName[index]}\n"
        }
        ingredientsListString = ingredientsListString.dropLast(1)
        _ingredientsList.postValue(ingredientsListString)
    }
}