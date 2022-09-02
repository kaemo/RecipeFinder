package pl.kaemo.recipefinder.ui.recipeDetailsActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.kaemo.recipefinder.ui.util.UiMessage

class RecipeDetailsViewModel : ViewModel() {

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
}