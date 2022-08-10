package pl.kaemo.recipefinder.domain.UseCase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.kaemo.recipefinder.common.Resource
import pl.kaemo.recipefinder.data.Model.SearchRecipesByIngredientsDTO.toRecipeByIngredients
import pl.kaemo.recipefinder.domain.Model.RecipeByIngredients
import pl.kaemo.recipefinder.domain.Repository.RecipeByIngredientsRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRecipesByIngredientsUseCase @Inject constructor(
    private val repository: RecipeByIngredientsRepository
) {
    operator fun invoke(): Flow<Resource<List<RecipeByIngredients>>> = flow {
        try {
            emit(Resource.Loading())
            val recipesByIngregients =
                repository.getRecipesByIngredients().map { it.toRecipeByIngredients() }
            emit(Resource.Success(recipesByIngregients))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}