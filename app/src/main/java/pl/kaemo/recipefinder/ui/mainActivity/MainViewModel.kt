package pl.kaemo.recipefinder.ui.mainActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.kaemo.recipefinder.domain.FakeRecipeService
import pl.kaemo.recipefinder.domain.RecipeService
import pl.kaemo.recipefinder.domain.model.RecipePreview

class MainViewModel : ViewModel() {

    private val recipeServiceImplementation: RecipeService = FakeRecipeService() //zmienna typu interfejsu

    private val ingredientsList = mutableListOf<String>()
    private val _ingredients = MutableLiveData<List<String>>(ingredientsList)
    val ingredients: LiveData<List<String>> = _ingredients

    private val _recipes = MutableLiveData<List<RecipePreview>>()
    val recipes: LiveData<List<RecipePreview>> = _recipes

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
            val recipePreviewList: List<RecipePreview> = recipeServiceImplementation.getRecipes(ingredientsList)
            _recipes.postValue(recipePreviewList)
        }
    }
}