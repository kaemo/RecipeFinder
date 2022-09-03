package pl.kaemo.recipefinder.ui.recipeDetailsActivity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import pl.kaemo.recipefinder.R
import pl.kaemo.recipefinder.domain.model.RecipeDetailsPreview
import pl.kaemo.recipefinder.ui.util.CustomLogger
import pl.kaemo.recipefinder.ui.util.LogcatLogger
import pl.kaemo.recipefinder.ui.util.SHARED_PREFS_KEY
import pl.kaemo.recipefinder.ui.util.showUiMessage

class RecipeDetailsActivity : AppCompatActivity() {

    private val logger: CustomLogger = LogcatLogger("RecipeDetailsActivity") // lub FileLogger()

    lateinit var viewModel: RecipeDetailsViewModel
    lateinit var sharedPrefs: SharedPreferences

    // clickable elements
    private lateinit var addToWishlistId: ImageButton
    private lateinit var clickableTextViewTimeId: TextView
    private lateinit var servingsId: ImageButton
    private lateinit var metricImperialId: Switch
    private lateinit var nutritionalServingsId: ImageButton
    private lateinit var recipeSourceNameLink: TextView

    // text from API
    private lateinit var recipeTitle: TextView
    private lateinit var recipeSummary: TextView
    private lateinit var recipeInstructions: TextView
    private lateinit var recipeReadyIn: TextView
    private lateinit var recipeServings: TextView

    // other
    private lateinit var recipeImage: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)

        viewModel = ViewModelProvider(this).get(RecipeDetailsViewModel::class.java)
        sharedPrefs = getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE)

        // clickable elements
        addToWishlistId = findViewById(R.id.imageButton)
        clickableTextViewTimeId = findViewById(R.id.clickable_textview_time)
        servingsId = findViewById(R.id.servings_image_button)
        metricImperialId = findViewById(R.id.measures_switch)
        nutritionalServingsId = findViewById(R.id.nutritional_serving)
        recipeSourceNameLink = findViewById(R.id.clickable_textview_source_name_link)

        // text from API
        recipeTitle = findViewById(R.id.main_title)
        recipeSummary = findViewById(R.id.summary_text)
        recipeInstructions = findViewById(R.id.preparation_text)
        recipeReadyIn = findViewById(R.id.clickable_textview_time)
        recipeServings = findViewById(R.id.activity_recipe_details_xml_servings_text)

        // other
        recipeImage = findViewById(R.id.activity_recipe_details_xml_recipe_image)

        val extraRecipeDetails = intent.getParcelableExtra<RecipeDetailsPreview>("extraRecipeId")

        recipeTitle.text = extraRecipeDetails?.title
        recipeSummary.text = extraRecipeDetails?.summary
        recipeInstructions.text = extraRecipeDetails?.instructions
        recipeReadyIn.text = getString(R.string.recipe_ready_in_minutes, extraRecipeDetails?.readyInMinutes)
        recipeServings.text = getString(R.string.recipe_servings, extraRecipeDetails?.servings)
        recipeSourceNameLink.text = extraRecipeDetails?.sourceName

        Glide.with(this)
            .load("https://spoonacular.com/recipeImages/${extraRecipeDetails?.id}-636x393.jpg")
            .into(recipeImage)

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

        recipeSourceNameLink.setOnClickListener {
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