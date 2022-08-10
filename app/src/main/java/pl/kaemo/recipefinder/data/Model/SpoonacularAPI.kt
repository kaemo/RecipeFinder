import pl.kaemo.recipefinder.data.Model.SearchRecipesByIngredientsDTO.RecipeByIngredientsDTO
import retrofit2.http.GET

interface SpoonacularAPI {

    @GET ("/recipes/findByIngredients?apiKey=df1ab77d91fe41a4b8b0012f3fc62dab&ingredients=apples,+flour,+sugar&number=30")
    suspend fun getRecipesByIngredients(): List<RecipeByIngredientsDTO>


}