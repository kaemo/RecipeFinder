package pl.kaemo.recipefinder.data.service

import kotlinx.coroutines.delay
import pl.kaemo.recipefinder.domain.RecipeService
import pl.kaemo.recipefinder.domain.model.RecipeDetailsPreview
import pl.kaemo.recipefinder.domain.model.RecipePreview
import pl.kaemo.recipefinder.domain.utils.Reply
import pl.kaemo.recipefinder.ui.util.CustomLogger
import pl.kaemo.recipefinder.ui.util.LogcatLogger

class FakeRecipeService : RecipeService {

    private val logger: CustomLogger = LogcatLogger("FakeRecipeService") // lub FileLogger()

    override suspend fun getRecipes(ingredients: List<String>): Reply<List<RecipePreview>> {
        logger.log("[FAKE DATA]")
        delay(1000L)

        return Reply.Success(
            listOf(
                RecipePreview(
                    1,
                    "Fake Slow Cooker Apple Pork Tenderloin",
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
        )
    }

    override suspend fun getRecipeDetails(recipeId: Int): Reply<RecipeDetailsPreview> {
        logger.log("[FAKE DATA]")
        delay(1000L)

        return try {

            Reply.Success(
                RecipeDetailsPreview(
                    id = recipeId,
                    "Fake Dessert Apple Rings With Cinnamon Cream Syrup",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                    33,
                    2,
                    "WebKnox",
                    "https://webknox.com/recipeImages/635578",
                    "jpg",
                    listOf(4.0, 0.25, 1.0, 0.5, 1.0),
                    listOf("null", "cup", "tablespoon", "cup", "serving"),
                    listOf(
                        "individual string cheese sticks",
                        "flour",
                        "egg + 2 water (egg wash)",
                        "Italian seasoned bread crumbs",
                        "Vegetable oil for frying"
                    )
                )
            )
        } catch (e: Exception) {
            Reply.Error(e)
        }
    }

}