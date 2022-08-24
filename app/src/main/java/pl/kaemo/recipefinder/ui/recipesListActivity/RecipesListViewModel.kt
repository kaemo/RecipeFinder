package pl.kaemo.recipefinder.ui.recipesListActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.kaemo.recipefinder.R
import pl.kaemo.recipefinder.domain.model.RecipePreview
import pl.kaemo.recipefinder.ui.util.UiMessage

class RecipesListViewModel : ViewModel() {

    private val recipesList = mutableListOf<RecipePreview>()

    private val _recipes = MutableLiveData<List<RecipePreview>>(recipesList)
    val recipes: LiveData<List<RecipePreview>> = _recipes


    fun onRecipesListActivityCreated(recipes: List<RecipePreview>) {
        for (recipe in recipes) {
            recipesList.add(recipe)
        }
        _recipes.postValue(recipesList)
    }

    // *********************************************

    private val _uiMessages = MutableLiveData<UiMessage>()
    val uiMessages: LiveData<UiMessage> = _uiMessages

    fun onTooltipClicked() {
        val dialog = UiMessage.Dialog(
            R.drawable.ic_baseline_info_24,
            R.string.recipes_list_activity_dialog_title,
            R.string.recipes_list_activity_dialog_message
        )
        _uiMessages.postValue(dialog)
    }

    fun onSortButtonClicked() {
        val toast = UiMessage.Toast("Sorting not implemented yet!")
        _uiMessages.postValue(toast)
    }

    fun onMoreButtonClicked() {
        val toast = UiMessage.Toast("Settings not implemented yet!")
        _uiMessages.postValue(toast)
    }
}