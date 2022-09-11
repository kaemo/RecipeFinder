package pl.kaemo.recipefinder.ui.recipeDetailsActivity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

    private lateinit var binding: ActivityRecipeDetailsBinding

    lateinit var viewModel: RecipeDetailsViewModel
    private lateinit var sharedPrefs: SharedPreferences

    private lateinit var extraRecipeDetails: RecipeDetailsPreview

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_details)

        logger.log("onCreate")

        viewModel = ViewModelProvider(this).get(RecipeDetailsViewModel::class.java)
        binding.viewModel = viewModel
        sharedPrefs = getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE)

        intent.getParcelableExtra<RecipeDetailsPreview>("extraRecipeId")?.let {
            extraRecipeDetails = it
        }

        // background
        loadRecipeImage(extraRecipeDetails.id, extraRecipeDetails.imageType)

        //title & time_servings section
        binding.recipeMainTitle.text = extraRecipeDetails.title
        binding.includeTimeServingsSection.clickableTextviewTime.text = getString(
            R.string.recipe_ready_in_minutes,
            extraRecipeDetails.readyInMinutes
        )
        binding.includeTimeServingsSection.activityRecipeDetailsXmlServingsText.text = getString(
            R.string.resource_strings_recipe_servings_plural,
            extraRecipeDetails.servings
        )

        // ingredients section

        binding.includeIngredientsSection.ingredientsList.text = viewModel.returnIngredientsString(
            extraRecipeDetails.extendedIngredientsAmount,
            extraRecipeDetails.extendedIngredientsUnit,
            extraRecipeDetails.extendedIngredientsOriginalName
        )

        // other
        binding.summaryContent.text = extraRecipeDetails.summary
        binding.preparationContent.text = extraRecipeDetails.instructions
        binding.sourceNameClickableLink.text =
            extraRecipeDetails.sourceName ?: getString(R.string.source_name_if_null)

        //nutritional section
        binding.includeNutriSection.nutritionalTitle.text =
            getString(R.string.resource_strings_nutritional_title_singular, 1)
        binding.includeNutriSection.nutritionalKcal.text = getString(R.string.nutritional_kcal)
        binding.includeNutriSection.nutritionalFat.text = getString(R.string.nutritional_fat)
        binding.includeNutriSection.nutritionalCarbs.text = getString(R.string.nutritional_carbs)
        binding.includeNutriSection.nutritionalProtein.text = getString(R.string.nutritional_prot)
        binding.includeNutriSection.detailsContent.text = getString(R.string.nutritional_list)

        observeUiMessages()

        binding.addToFavouritesButton.setOnClickListener {
            viewModel.onAddToWishlistClicked()
        }

        binding.includeTimeServingsSection.clickableTextviewTime.setOnClickListener {
            viewModel.onTextViewTimeClicked()
        }

        binding.includeTimeServingsSection.servingsViewClickableSection.setOnClickListener {
            viewModel.onServingsClicked()
        }

        binding.includeIngredientsSection.measuresSwitch.setOnClickListener {
            viewModel.onMetricSwitchClicked()
        }

        binding.sourceNameClickableLink.setOnClickListener {
            viewModel.onTextViewSourceLinkClicked()
        }

        binding.includeNutriSection.nutritionalServing.setOnClickListener {
            viewModel.onNutritionalServingsClicked()
        }

        binding.similarRecipesButton.setOnClickListener {
            viewModel.onSimilarRecipesButtonClicked()
        }

    }

    private fun loadRecipeImage(recipeId: Int?, imageType: String?) {

        Glide.with(this)
            .load("https://spoonacular.com/recipeImages/$recipeId-636x393.${imageType ?: "jpg"}")
            .placeholder(R.drawable.recipe_image_loading)
            .error(R.drawable.recipe_image_error)
            .into(binding.recipeMainImage)
    }

    private fun observeUiMessages() {
        viewModel.uiMessages.observe(this) { uiMessage ->
            showUiMessage(uiMessage)
        }
    }
}