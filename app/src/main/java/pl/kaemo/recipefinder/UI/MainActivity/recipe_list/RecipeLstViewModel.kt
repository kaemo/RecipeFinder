package pl.kaemo.recipefinder.UI.MainActivity.recipe_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import pl.kaemo.recipefinder.common.Resource
import pl.kaemo.recipefinder.domain.UseCase.GetRecipesByIngredientsUseCase
import javax.inject.Inject

@HiltViewModel

class RecipeLstViewModel @Inject constructor(
    private val getRecipesByIngredientsUseCase: GetRecipesByIngredientsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(RecipeListState())
    val state: State<RecipeListState> = _state

    init {
        getRecipes()
    }

    private fun getRecipes() {
        getRecipesByIngredientsUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = RecipeListState(recipesByIngredients = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = RecipeListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = RecipeListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}