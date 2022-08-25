package pl.kaemo.recipefinder.domain

import kotlinx.coroutines.delay
import pl.kaemo.recipefinder.domain.model.RecipePreview

interface RecipeService {
    suspend fun getRecipes(ingredients: List<String>): List<RecipePreview>
}

class FakeRecipeService : RecipeService {
    override suspend fun getRecipes(ingredients: List<String>): List<RecipePreview> {
        delay(1000L)
        return listOf(
            RecipePreview(
                "Slow Cooker Apple Pork Tenderloin",
                "https://spoonacular.com/recipeImages/673463-312x231.jpg",
                null
            ),
            RecipePreview(
                "Apple Pie with PB&J Streusel",
                "https://spoonacular.com/recipeImages/632583-312x231.jpg",
                null
            ),
            RecipePreview(
                "Slow Cooked Applesauce",
                "https://spoonacular.com/recipeImages/660261-312x231.jpg",
                null
            ),
            RecipePreview(
                "Cranberry Apple Crisp",
                "https://spoonacular.com/recipeImages/640352-312x231.jpg",
                listOf("2 teaspoons chopped fresh rosemary leaves")
            ),
            RecipePreview(
                "Apricot Glazed Apple Tart",
                "https://spoonacular.com/recipeImages/632660-312x231.jpg",
                listOf("green onion tops optional")
            ),
            RecipePreview(
                "Traditional Apple Tart",
                "https://spoonacular.com/recipeImages/663748-312x231.jpg",
                listOf("6-8 flour tortillas torn into bite sized pieces")
            ),
            RecipePreview(
                "Canadian apple butter",
                "https://spoonacular.com/recipeImages/632555-312x231.jpg",
                listOf("1 package dry yeast")
            ),
            RecipePreview(
                "Easy & Delish! ~ Apple Crumble",
                "https://spoonacular.com/recipeImages/660268-312x231.jpg",
                listOf("¾ cup fresh basil leaves (loosely packed), chopped")
            ),
            RecipePreview(
                "Apple Or Peach Strudel",
                "https://spoonacular.com/recipeImages/640353-312x231.jpg",
                listOf("2 10oz cans verde enchilada sauce", "1 bunch kale")
            ),
            RecipePreview(
                "Dessert Apple Rings With Cinnamon Cream Syrup",
                "https://spoonacular.com/recipeImages/632666-312x231.jpg",
                listOf(
                    "1/2 pound extra lean beef, ground",
                    "1 pound fresh rhubarb",
                    "1/4 cup honey",
                    "1 15oz can refried beans"
                )
            )
        )
    }

}