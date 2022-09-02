package pl.kaemo.recipefinder.ui.recipeDetailsActivity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import pl.kaemo.recipefinder.R
import pl.kaemo.recipefinder.ui.util.NavigationManager.navigateToRecipesListActivity
import pl.kaemo.recipefinder.ui.util.SHARED_PREFS_KEY
import pl.kaemo.recipefinder.ui.util.showUiMessage

class RecipeDetailsActivity : AppCompatActivity() {

    lateinit var viewModel: RecipeDetailsViewModel
    lateinit var sharedPrefs: SharedPreferences

    private lateinit var addToWishlistId: ImageButton
    private lateinit var clickableTextViewTimeId: TextView
    private lateinit var servingsId: ImageButton
    private lateinit var metricImperialId: Switch
    private lateinit var sourceLinkId: TextView
    private lateinit var nutritionalServingsId: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)

        viewModel = ViewModelProvider(this).get(RecipeDetailsViewModel::class.java)
        sharedPrefs = getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE)

        addToWishlistId = findViewById(R.id.imageButton)
        clickableTextViewTimeId = findViewById(R.id.clickable_textview_time)
        servingsId = findViewById(R.id.servings_image_button)
        metricImperialId = findViewById(R.id.measures_switch)
        sourceLinkId = findViewById(R.id.clickable_textview_source_link)
        nutritionalServingsId = findViewById(R.id.nutritional_serving)

        //findViewById<TextView>(R.id.costera).text = "ID: ${intent.getIntExtra("extraRecipeId", 0)}"

        observeUiMessages()

        addToWishlistId.setOnClickListener {
            viewModel.onAddToWishlistClicked()
        }

        clickableTextViewTimeId.setOnClickListener {
            viewModel.onTextViewTimeClicked()
        }

        servingsId.setOnClickListener {
            viewModel.onServingsClicked()
        }

        metricImperialId.setOnClickListener {
            viewModel.onMetricSwitchClicked()
        }

        sourceLinkId.setOnClickListener {
            viewModel.onTextViewSourceLinkClicked()
        }

        nutritionalServingsId.setOnClickListener {
            viewModel.onNutritionalServingsClicked()
        }

    }

    private fun observeUiMessages() {
        viewModel.uiMessages.observe(this) { uiMessage ->
            showUiMessage(uiMessage)
        }
    }
}