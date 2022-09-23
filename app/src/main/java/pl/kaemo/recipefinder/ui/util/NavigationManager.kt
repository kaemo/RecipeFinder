package pl.kaemo.recipefinder.ui.util

import android.app.Activity
import android.content.Intent
import pl.kaemo.recipefinder.domain.model.RecipeDetailsPreview
import pl.kaemo.recipefinder.domain.model.RecipePreview
import pl.kaemo.recipefinder.ui.errorScreenActivity.ErrorScreenActivity
import pl.kaemo.recipefinder.ui.favouritesActivity.FavouritesActivity
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
        recipeDetails: RecipeDetailsPreview
    ) {
        Intent(this, RecipeDetailsActivity::class.java).also {
            it.putExtra("extraRecipeId", recipeDetails)
            startActivity(it)
        }
    }

    fun Activity.navigateToErrorScreenActivity(
        errorMessage: String
    ) {
        Intent(this, ErrorScreenActivity::class.java).also {
            it.putExtra("extraErrorMessage", errorMessage)
            startActivity(it)
        }
    }

    fun Activity.navigateToFavouritesActivity() {
        Intent(this, FavouritesActivity::class.java).also {
            startActivity(it)
        }
    }

}