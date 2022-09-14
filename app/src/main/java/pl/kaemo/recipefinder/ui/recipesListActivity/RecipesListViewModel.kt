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
import pl.kaemo.recipefinder.domain.utils.Reply
import pl.kaemo.recipefinder.ui.util.CustomLogger
import pl.kaemo.recipefinder.ui.util.LogcatLogger
import pl.kaemo.recipefinder.ui.util.UiMessage
import javax.inject.Inject

@HiltViewModel
class RecipesListViewModel @Inject constructor(
    private val recipeService: RecipeService
) : ViewModel() {

    private val logger: CustomLogger = LogcatLogger("RecipesListVM") // lub FileLogger()

    private val recipesList = mutableListOf<RecipePreview>()
    private val _recipes = MutableLiveData<List<RecipePreview>>(recipesList)
    val recipes: LiveData<List<RecipePreview>> = _recipes

    private val _uiMessages = MutableLiveData<UiMessage>()
    val uiMessages: LiveData<UiMessage> = _uiMessages

    private val _recipeDetails = MutableLiveData<RecipeDetailsPreview>()
    val recipeDetails: LiveData<RecipeDetailsPreview> = _recipeDetails

    private val _apiError = MutableLiveData<String>()
    val apiError: LiveData<String> = _apiError

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
        viewModelScope.launch {
            val reply = recipeService.getRecipeDetails(recipeId)
            logger.log(reply.toString())
            when (reply) {
                is Reply.Success -> {
                    _recipeDetails.postValue(reply.data)
                }
                is Reply.Error -> {
                    _apiError.postValue(reply.error.toString())
                }
            }
        }
    }


}