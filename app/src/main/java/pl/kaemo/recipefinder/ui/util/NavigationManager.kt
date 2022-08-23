package pl.kaemo.recipefinder.ui.util

import android.app.Activity
import android.content.Intent
import pl.kaemo.recipefinder.ui.recipesListActivity.RecipesListActivity

object NavigationManager {

    fun Activity.navigateToRecipesListActivity() {
        startActivity(Intent(this, RecipesListActivity::class.java))
    }

}