package pl.kaemo.recipefinder.ui.mainActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.kaemo.recipefinder.domain.FakeRecipeService
import pl.kaemo.recipefinder.domain.model.RecipePreview

class MainViewModel : ViewModel() {

    private val ingredientsList = mutableListOf<String>()

    private val _ingredients = MutableLiveData<List<String>>(ingredientsList)
    val ingredients: LiveData<List<String>> = _ingredients

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

    private val recipesList = mutableListOf<RecipePreview>()
    private val _recipes = MutableLiveData<List<RecipePreview>>()
    val recipes: LiveData<List<RecipePreview>> = _recipes

    fun onButtonSearchRecipesClicked() {
        val fakeRecipeServiceImplementation = FakeRecipeService()
        val recipePreviewList: List<RecipePreview> = fakeRecipeServiceImplementation.getRecipes(ingredientsList)

        for (recipePreview in recipePreviewList) {
            recipesList.add(recipePreview)
        }
        _recipes.postValue(recipesList)
    }
}