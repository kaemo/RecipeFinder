package pl.kaemo.recipefinder.domain

import kotlinx.coroutines.delay
import pl.kaemo.recipefinder.domain.model.RecipePreview

interface RecipeService {
    suspend fun getRecipes(ingredients: List<String>): List<RecipePreview>
}

class FakeRecipeService : RecipeService { //dlaczego to jest tu? - a nie jako osobny plik w warstwie data // struktura po wywaleniu fekowych danych - dalej RecipeService w takiej formie?
    override suspend fun getRecipes(ingredients: List<String>): List<RecipePreview> {
        delay(3000L)
        return listOf(
            RecipePreview(
                1,
                "Slow Cooker Apple Pork Tenderloin",
                "https://spoonacular.com/recipeImages/673463-312x231.jpg",
                null
            ),
            RecipePreview(
                2,
                "Apple Pie with PB&J Streusel",
                "https://spoonacular.com/recipeImages/632583-312x231.jpg",
                null
            ),
            RecipePreview(
                3,
                "Slow Cooked Applesauce",
                "https://spoonacular.com/recipeImages/660261-312x231.jpg",
                null
            ),
            RecipePreview(
                4,
                "Cranberry Apple Crisp",
                "https://spoonacular.com/recipeImages/640352-312x231.jpg",
                listOf("2 teaspoons chopped fresh rosemary leaves")
            ),
            RecipePreview(
                5,
                "Apricot Glazed Apple Tart",
                "https://spoonacular.com/recipeImages/632660-312x231.jpg",
                listOf("green onion tops optional")
            ),
            RecipePreview(
                6,
                "Traditional Apple Tart",
                "https://spoonacular.com/recipeImages/663748-312x231.jpg",
                listOf("6-8 flour tortillas torn into bite sized pieces")
            ),
            RecipePreview(
                7,
                "Canadian apple butter",
                "https://spoonacular.com/recipeImages/632555-312x231.jpg",
                listOf("1 package dry yeast")
            ),
            RecipePreview(
                8,
                "Easy & Delish! ~ Apple Crumble",
                "https://spoonacular.com/recipeImages/660268-312x231.jpg",
                listOf("¾ cup fresh basil leaves (loosely packed), chopped")
            ),
            RecipePreview(
                9,
                "Apple Or Peach Strudel",
                "https://spoonacular.com/recipeImages/640353-312x231.jpg",
                listOf("2 10oz cans verde enchilada sauce", "1 bunch kale")
            ),
            RecipePreview(
                10,
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