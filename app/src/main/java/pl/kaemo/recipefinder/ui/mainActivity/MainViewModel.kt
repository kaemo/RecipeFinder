package pl.kaemo.recipefinder.ui.mainActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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
}