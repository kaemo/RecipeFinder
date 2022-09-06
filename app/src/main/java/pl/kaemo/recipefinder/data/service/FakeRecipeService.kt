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

        TODO("do zrobienia")

//        return listOf(
//            RecipePreview(
//                1,
//                "Slow Cooker Apple Pork Tenderloin",
//                "https://spoonacular.com/recipeImages/673463-312x231.jpg",
//                null
//            ),
//            RecipePreview(
//                2,
//                "Apple Pie with PB&J Streusel",
//                "https://spoonacular.com/recipeImages/632583-312x231.jpg",
//                null
//            ),
//            RecipePreview(
//                3,
//                "Slow Cooked Applesauce",
//                "https://spoonacular.com/recipeImages/660261-312x231.jpg",
//                null
//            ),
//            RecipePreview(
//                4,
//                "Cranberry Apple Crisp",
//                "https://spoonacular.com/recipeImages/640352-312x231.jpg",
//                listOf("2 teaspoons chopped fresh rosemary leaves")
//            ),
//            RecipePreview(
//                5,
//                "Apricot Glazed Apple Tart",
//                "https://spoonacular.com/recipeImages/632660-312x231.jpg",
//                listOf("green onion tops optional")
//            ),
//            RecipePreview(
//                6,
//                "Traditional Apple Tart",
//                "https://spoonacular.com/recipeImages/663748-312x231.jpg",
//                listOf("6-8 flour tortillas torn into bite sized pieces")
//            ),
//            RecipePreview(
//                7,
//                "Canadian apple butter",
//                "https://spoonacular.com/recipeImages/632555-312x231.jpg",
//                listOf("1 package dry yeast")
//            ),
//            RecipePreview(
//                8,
//                "Easy & Delish! ~ Apple Crumble",
//                "https://spoonacular.com/recipeImages/660268-312x231.jpg",
//                listOf("¾ cup fresh basil leaves (loosely packed), chopped")
//            ),
//            RecipePreview(
//                9,
//                "Apple Or Peach Strudel",
//                "https://spoonacular.com/recipeImages/640353-312x231.jpg",
//                listOf("2 10oz cans verde enchilada sauce", "1 bunch kale")
//            ),
//            RecipePreview(
//                10,
//                "Dessert Apple Rings With Cinnamon Cream Syrup",
//                "https://spoonacular.com/recipeImages/632666-312x231.jpg",
//                listOf(
//                    "1/2 pound extra lean beef, ground",
//                    "1 pound fresh rhubarb",
//                    "1/4 cup honey",
//                    "1 15oz can refried beans"
//                )
//            )
//        )
    }

    override suspend fun getRecipeDetails(recipeId: Int): Reply<RecipeDetailsPreview> {
        logger.log("[FAKE DATA]")

        TODO("do zrobienia")

//        RecipeDetailsPreview(
//            id = 643809,
//            title = "Fried String Cheese Sticks",
//            summary = "You can never have too many hor d'oeuvre recipes, so give Fried String Cheese Sticks a try. This recipe serves 1 and costs $3.09 per serving. One serving contains <b>697 calories</b>, <b>38g of protein</b>, and <b>30g of fat</b>. 1 person has tried and liked this recipe. A mixture of individual string cheese sticks, vegetable oil, seasoned bread crumbs, and a handful of other ingredients are all it takes to make this recipe so flavorful. It is brought to you by Foodista. From preparation to the plate, this recipe takes around <b>around 45 minutes</b>. Taking all factors into account, this recipe <b>earns a spoonacular score of 43%</b>, which is solid. If you like this recipe, you might also like recipes such as <a href= https ://spoonacular.com/recipes/fried-cheese-sticks-477354>Fried Cheese Sticks</a>, <a href=https//spoonacular.com/recipes/fried-cheese-sticks-245493>Fried Cheese Sticks</a>, and <a href=http//spoonacular.com/recipes/fried-mozzarella-cheese-sticks-244156>Fried Mozzarella Cheese Sticks</a>., instructions=Dredge individual cheese sticks in flour. shake off excess. Roll sticks in egg wash,Then coat with bread crumbs. Fry in hot oil for 20 seconds, then flip and fry another 20 seconds. Serve with Pizza sauce or marinara!!",
//            instructions = "",
//            readyInMinutes = 45,
//            servings = 1,
//            sourceName = "Foodista",
//            sourceUrl = "www.foodista.com/recipe/NXYMZP8D/fried-string-cheese-sticks-recipe",
//            imageType = "jpg",
//            extendedIngredientsAmount = listOf(4.0, 0.25, 1.0, 0.5, 1.0),
//            extendedIngredientsUnit = listOf("null", "cup", "tablespoon", "cup", "serving"),
//            extendedIngredientsOriginalName = listOf(
//                "individual string cheese sticks",
//                "flour",
//                "egg + 2 water (egg wash)",
//                "Italian seasoned bread crumbs",
//                "Vegetable oil for frying"
//            )
//        )
    }

}