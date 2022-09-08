package pl.kaemo.recipefinder.ui.recipeDetailsActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.kaemo.recipefinder.domain.model.RecipeDetailsPreview
import pl.kaemo.recipefinder.ui.util.UiMessage
import pl.kaemo.recipefinder.ui.util.trimIfMoreDecimalThan

class RecipeDetailsViewModel : ViewModel() {

    private val _uiMessages = MutableLiveData<UiMessage>()
    val uiMessages: LiveData<UiMessage> = _uiMessages

    //DS: or each value independently
    private val _recipeDetails = MutableLiveData<RecipeDetailsPreview>()
    val recipeDetails: LiveData<RecipeDetailsPreview> = _recipeDetails

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

    fun returnIngredientsString(
        amountList: List<Double>,
        unitList: List<String>,
        nameList: List<String>
    ): String {
        var ingredientsListString = ""
        amountList.forEachIndexed { index, it ->
            ingredientsListString += "• ${
                it.trimIfMoreDecimalThan(2).toString().trimEnd { it == '0' }.trimEnd { it == '.' }
            } ${unitList[index]} ${nameList[index]}\n"
        }
        ingredientsListString = ingredientsListString.dropLast(1)
        return ingredientsListString
    }

    fun setRecipeDetails(details: RecipeDetailsPreview) {
        _recipeDetails.postValue(details)
    }
}