package pl.kaemo.recipefinder.domain

import pl.kaemo.recipefinder.domain.Model.RecipePreview

interface RecipeService {
    fun getRecipes(ingredients: List<String>): List<RecipePreview>
}

class FakeRecipeService: RecipeService{
    override fun getRecipes(ingredients: List<String>): List<RecipePreview> {
        return listOf(
            RecipePreview("Pizza", null, "Short sample description"),
            RecipePreview("Kebab", null, "Short sample description 2"),
            RecipePreview("Currosant", null, "Short sample description 3")
        )
    }

}