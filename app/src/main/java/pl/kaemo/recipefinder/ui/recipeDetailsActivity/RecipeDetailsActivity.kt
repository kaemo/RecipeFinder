package pl.kaemo.recipefinder.ui.recipeDetailsActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import pl.kaemo.recipefinder.R

class RecipeDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)

        //findViewById<TextView>(R.id.costera).text = "ID: ${intent.getIntExtra("extraRecipeId", 0)}"
    }
}