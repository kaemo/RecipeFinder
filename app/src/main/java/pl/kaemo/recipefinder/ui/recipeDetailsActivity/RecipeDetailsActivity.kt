package pl.kaemo.recipefinder.ui.recipeDetailsActivity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import pl.kaemo.recipefinder.R
import pl.kaemo.recipefinder.databinding.ActivityRecipeDetailsBinding
import pl.kaemo.recipefinder.domain.model.RecipeDetailsPreview
import pl.kaemo.recipefinder.ui.util.CustomLogger
import pl.kaemo.recipefinder.ui.util.LogcatLogger
import pl.kaemo.recipefinder.ui.util.SHARED_PREFS_KEY
import pl.kaemo.recipefinder.ui.util.showUiMessage

class RecipeDetailsActivity : AppCompatActivity() {

    private val logger: CustomLogger = LogcatLogger("RecipeDetailsActivity") // lub FileLogger()

    //DS: declared binding property
    lateinit var binding: ActivityRecipeDetailsBinding
    lateinit var viewModel: RecipeDetailsViewModel
    lateinit var sharedPrefs: SharedPreferences

    lateinit var extraRecipeDetails: RecipeDetailsPreview

    //DS: all variables containing views can be deleted and replaced by binding.<name> like in line 127 here
    // background
    private lateinit var recipeImage: ImageView
    private lateinit var addToWishlistId: ImageButton

    // title & time_servings section
    private lateinit var recipeTitle: TextView
    private lateinit var clickableTextViewTimeId: TextView
    private lateinit var servingsSectionId: ConstraintLayout

    // ingredients section
    private lateinit var metricImperialId: Switch
    private lateinit var recipeReadyIn: TextView
    private lateinit var recipeServings: TextView
    private lateinit var recipeIngredients: TextView

    // other
    private lateinit var recipeSummary: TextView
    private lateinit var recipeInstructions: TextView
    private lateinit var recipeSourceNameLink: TextView
    private lateinit var similarRecipesButtonId: Button

    //nutritional section
    private lateinit var nutritionalTitle: TextView
    private lateinit var nutritionalServingsId: ImageButton
    private lateinit var nutritionalKcal: TextView
    private lateinit var nutritionalFat: TextView
    private lateinit var nutritionalCarbs: TextView
    private lateinit var nutritionalProtein: TextView
    private lateinit var nutritionalDetails: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //DS: Assigned value to binding property
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_recipe_details
        )

        logger.log("onCreate")

        viewModel = ViewModelProvider(this).get(RecipeDetailsViewModel::class.java).also {
            //DS: assigned value to variable declared in xml (line 7)
            binding.viewModel = it
        }
        sharedPrefs = getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE)

        // background
        recipeImage = findViewById(R.id.activity_recipe_details_xml_recipe_image)
        addToWishlistId = findViewById(R.id.imageButton)

        //title & time_servings section
        recipeTitle = findViewById(R.id.main_title)
        clickableTextViewTimeId = findViewById(R.id.clickable_textview_time)
        servingsSectionId = findViewById(R.id.servings_view_clickable_section)

        // ingredients section
        metricImperialId = findViewById(R.id.measures_switch)
        recipeReadyIn = findViewById(R.id.clickable_textview_time)
        recipeServings = findViewById(R.id.activity_recipe_details_xml_servings_text)
        recipeIngredients = findViewById(R.id.ingredients_list)

        // other
        recipeSummary = findViewById(R.id.summary_text)
        recipeInstructions = findViewById(R.id.preparation_text)
        recipeSourceNameLink = findViewById(R.id.clickable_textview_source_name_link)
        similarRecipesButtonId = findViewById(R.id.similar_recipes_button)

        //nutritional section
        nutritionalTitle = findViewById(R.id.nutritional_title)
        nutritionalServingsId = findViewById(R.id.nutritional_serving)
        nutritionalKcal = findViewById(R.id.nutrions_layout_card_calories_number)
        nutritionalFat = findViewById(R.id.nutrions_fat)
        nutritionalCarbs = findViewById(R.id.nutrions_carbs)
        nutritionalProtein = findViewById(R.id.nutrions_protein)
        nutritionalDetails = findViewById(R.id.details_more)

        /* -------------------------------------------------------------------------------------- */

        intent.getParcelableExtra<RecipeDetailsPreview>("extraRecipeId")?.let {
            extraRecipeDetails = it
            //DS: pass it to view model
            viewModel.setRecipeDetails(it)
        }

        // background
        loadRecipeImage(extraRecipeDetails.id, extraRecipeDetails.imageType)

        //title & time_servings section
//        recipeTitle.text = extraRecipeDetails.title
        //DS: Instead of line above, now can use view elements like below:
        binding.mainTitle.text = extraRecipeDetails.title

        // ingredients section
        recipeReadyIn.text = getString(
            R.string.recipe_ready_in_minutes,
            extraRecipeDetails.readyInMinutes
        )
        recipeServings.text = getString(
            R.string.resource_strings_recipe_servings_plural,
            extraRecipeDetails.servings
        )
        recipeIngredients.text = viewModel.returnIngredientsString(
            extraRecipeDetails.extendedIngredientsAmount,
            extraRecipeDetails.extendedIngredientsUnit,
            extraRecipeDetails.extendedIngredientsOriginalName
        )

        // other
//        recipeSummary.text = extraRecipeDetails.summary
        //DS: Instead of line above, now can assign value in xml -> line 121
        recipeInstructions.text = extraRecipeDetails.instructions
        recipeSourceNameLink.text = extraRecipeDetails.sourceName ?: ">> Click here to open the external site <<"

        //nutritional section
        nutritionalTitle.text = getString(R.string.resource_strings_nutritional_title_singular, 1)
        nutritionalKcal.text = "647"
        nutritionalFat.text = "Total fat 36g (30%)"
        nutritionalCarbs.text = "Carbs 18g (34%)"
        nutritionalProtein.text = "Proteins 23g (46%)"
        nutritionalDetails.text = "[FAKE DATA]\nProtein 23g (48% of daily need)\nVitamin B3 6mg (33% od daily need)\nVitamin B12 1ug (33% of daily need)\nZinc 4 mg (29% of daily need)\nProtein 23g (48% of daily need)\nVitamin B3 6mg (33% od daily need)\nVitamin B12 1ug (33% of daily need)\nZinc 4 mg (29% of daily need)\nProtein 23g (48% of daily need)\nVitamin B3 6mg (33% od daily need)\nVitamin B12 1ug (33% of daily need)\nZinc 4 mg (29% of daily need)\nProtein 23g (48% of daily need)\nVitamin B3 6mg (33% od daily need)\nVitamin B12 1ug (33% of daily need)\nZinc 4 mg (29% of daily need)"

        observeUiMessages()

        addToWishlistId.setOnClickListener {
            viewModel.onAddToWishlistClicked()
        }

        clickableTextViewTimeId.setOnClickListener {
            viewModel.onTextViewTimeClicked()
        }

        servingsSectionId.setOnClickListener {
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

//        similarRecipesButtonId.setOnClickListener {
//            viewModel.onSimilarRecipesButtonClicked()
//        }
        //DS: Instead of lines above, can assign function callback like in xml -> line 211

    }

    private fun loadRecipeImage(recipeId: Int?, imageType: String?) {

        Glide.with(this)
            .load("https://spoonacular.com/recipeImages/$recipeId-636x393.${imageType ?: "jpg"}")
            .placeholder(R.drawable.recipe_image_loading)
            .error(R.drawable.recipe_image_error)
            .into(recipeImage)
    }

    private fun observeUiMessages() {
        viewModel.uiMessages.observe(this) { uiMessage ->
            showUiMessage(uiMessage)
        }
    }
}