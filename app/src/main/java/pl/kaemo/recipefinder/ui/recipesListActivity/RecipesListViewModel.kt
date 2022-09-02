package pl.kaemo.recipefinder.ui.recipesListActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pl.kaemo.recipefinder.R
import pl.kaemo.recipefinder.domain.RecipeService
import pl.kaemo.recipefinder.domain.model.RecipeDetailsPreview
import pl.kaemo.recipefinder.domain.model.RecipePreview
import pl.kaemo.recipefinder.ui.util.UiMessage
import javax.inject.Inject

@HiltViewModel
class RecipesListViewModel @Inject constructor(
    private val recipeService: RecipeService
) : ViewModel() {

    private val recipesList = mutableListOf<RecipePreview>()
    private val _recipes = MutableLiveData<List<RecipePreview>>(recipesList)
    val recipes: LiveData<List<RecipePreview>> = _recipes

    private val _uiMessages = MutableLiveData<UiMessage>()
    val uiMessages: LiveData<UiMessage> = _uiMessages

    private val _recipeDetails = MutableLiveData<List<RecipeDetailsPreview>>()
    val recipeDetails: LiveData<List<RecipeDetailsPreview>> = _recipeDetails

    fun onRecipesListActivityCreated(recipes: List<RecipePreview>) {
        recipesList.addAll(recipes)
        _recipes.postValue(recipesList)
    }

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

    fun onRecipeClicked(recipeId: Int) {

        val toast = UiMessage.Toast("[recipeId: $recipeId]")
        _uiMessages.postValue(toast)

        viewModelScope.launch {
            val recipeDetailsPreviewList: List<RecipeDetailsPreview> =
                recipeService.getRecipeDetails(recipeId)
            _recipeDetails.postValue(recipeDetailsPreviewList)
        }
    }
}