package pl.kaemo.recipefinder.ui.recipeDetailsActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.kaemo.recipefinder.R
import pl.kaemo.recipefinder.domain.model.RecipeDetailsPreview
import pl.kaemo.recipefinder.domain.utils.StringRepository
import pl.kaemo.recipefinder.ui.util.CustomLogger
import pl.kaemo.recipefinder.ui.util.LogcatLogger
import pl.kaemo.recipefinder.ui.util.UiMessage
import pl.kaemo.recipefinder.ui.util.trimIfMoreDecimalThan
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    stringRepository: StringRepository,
    savedState: SavedStateHandle
) : ViewModel() {

    val logger: CustomLogger = LogcatLogger("RecipeDetailsVM") // lub FileLogger()

    val extraRecipeDetails: RecipeDetailsPreview? =
        savedState.get<RecipeDetailsPreview>("extraRecipeId")

    private val _ingredientsList = MutableLiveData("loading...")
    val ingredientsList: LiveData<String> = _ingredientsList

    private val _uiMessages = MutableLiveData<UiMessage>()
    val uiMessages: LiveData<UiMessage> = _uiMessages

    /* String variables */

    val recipeReadyInMinutes: String = stringRepository.getString(
        R.string.recipe_ready_in_minutes,
        extraRecipeDetails?.readyInMinutes ?: "0"
    )
    val recipeServingsPlural: String = stringRepository.getString(
        R.string.resource_strings_recipe_servings_plural,
        extraRecipeDetails?.servings ?: "1"
    )
    val sourceNameLink: String =
        extraRecipeDetails?.sourceName ?: stringRepository.getString(R.string.source_name_if_null)

    val nutritionalTitle: String = stringRepository.getString(
        R.string.resource_strings_nutritional_title_singular,
        1
    )

    /* ------------ */

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
        _ingredientsList.postValue(ingredientsListString.dropLast(1))
    }

}