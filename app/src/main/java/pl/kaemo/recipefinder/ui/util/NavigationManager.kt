package pl.kaemo.recipefinder.ui.util

import android.app.Activity
import android.content.Intent
import pl.kaemo.recipefinder.domain.model.RecipePreview
import pl.kaemo.recipefinder.ui.recipeDetailsActivity.RecipeDetailsActivity
import pl.kaemo.recipefinder.ui.recipesListActivity.RecipesListActivity

object NavigationManager {

    fun Activity.navigateToRecipesListActivity(
        recipesList: List<RecipePreview>
    ) {
        Intent(this, RecipesListActivity::class.java).also {
            it.putParcelableArrayListExtra("extraRecipesList", ArrayList(recipesList))
            startActivity(it)
        }
    }

    fun Activity.navigateToRecipeDetailsActivity(
        recipeId: Int
    ) {
        Intent(this, RecipeDetailsActivity::class.java).also {
            it.putExtra("extraRecipeId", recipeId)
            startActivity(it)
        }
    }

}