package pl.kaemo.recipefinder.UI.MainActivity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.kaemo.recipefinder.domain.FakeRecipeService
import pl.kaemo.recipefinder.domain.UseCase.GetRecipesForIngredientsUseCase

class MainViewModel : ViewModel() {
    private var counter = 0
    var greeting = MutableLiveData<String>("Hello app updated")

    val ingredientsList = mutableListOf<String>()
    val getRecipesForIngredientsUseCase = GetRecipesForIngredientsUseCase(FakeRecipeService())

    fun onIngredientAdded(name: String) {
        //if empty - validate
        //else
        ingredientsList.add(name)
    }

    fun onButtonClicked(){
        counter++
//        greeting.value = "Button was clicked $counter times"
        Log.d("TAG", "Counter is $counter")

        try {
            val recipes = getRecipesForIngredientsUseCase.execute(listOf())
            greeting.value = recipes.toString()
            Log.d("TAG", "Got recipes: $recipes")
        } catch (e: Exception) {
            greeting.value = "Empty list. Try again."
            Log.d("TAG", "Got exception")
        }
    }
}