package pl.kaemo.recipefinder.ui.mainActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pl.kaemo.recipefinder.domain.RecipeService
import pl.kaemo.recipefinder.domain.model.RecipePreview
import pl.kaemo.recipefinder.domain.utils.Reply
import pl.kaemo.recipefinder.ui.util.CustomLogger
import pl.kaemo.recipefinder.ui.util.LogcatLogger
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val recipeService: RecipeService
) : ViewModel() {

    private val logger: CustomLogger = LogcatLogger("MainViewModel") // lub FileLogger()

    private val ingredientsList = mutableListOf<String>()
    private val _ingredients = MutableLiveData<List<String>>(ingredientsList)
    val ingredients: LiveData<List<String>> = _ingredients

    private val _recipes = MutableLiveData<List<RecipePreview>>()
    val recipes: LiveData<List<RecipePreview>> = _recipes

    private val _apiError = MutableLiveData<String>()
    val apiError: LiveData<String> = _apiError

    fun onIngredientAdded(name: String) {
        ingredientsList.add(name)
        _ingredients.postValue(ingredientsList)
    }

    fun onIngredientDeleted(index: Int) {
        ingredientsList.removeAt(index)
        _ingredients.postValue(ingredientsList)
    }

    fun enoughIngredients(): Boolean {
        return ingredientsList.size >= 1
    }

    fun onButtonSearchRecipesClicked() {
        viewModelScope.launch {
            val reply = recipeService.getRecipes(ingredientsList)
            when (reply) {
                is Reply.Success -> {
                    _recipes.postValue(reply.data)
                }
                is Reply.Error -> {
                    logger.log(reply.error.toString())
                    _apiError.postValue(reply.error.toString())
                }
            }
        }
    }

}