package pl.kaemo.recipefinder.ui.recipeDetailsActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import pl.kaemo.recipefinder.R
import pl.kaemo.recipefinder.databinding.ActivityRecipeDetailsBinding
import pl.kaemo.recipefinder.ui.util.CustomLogger
import pl.kaemo.recipefinder.ui.util.LogcatLogger
import pl.kaemo.recipefinder.ui.util.showUiMessage

class RecipeDetailsActivity : AppCompatActivity() {

    private val logger: CustomLogger = LogcatLogger("RecipeDetailsActivity") // lub FileLogger()

    private lateinit var binding: ActivityRecipeDetailsBinding

    lateinit var viewModel: RecipeDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_details)
        binding.lifecycleOwner = this

        logger.log("onCreate")

        viewModel = ViewModelProvider(this).get(RecipeDetailsViewModel::class.java)
        binding.viewModel = viewModel

        loadRecipeImage()
        observeUiMessages()

        //title & time_servings section //string repository
        binding.includeTimeServingsSection.clickableTextviewTime.text =
            getString(
                R.string.recipe_ready_in_minutes,
                viewModel.extraRecipeDetails?.readyInMinutes
            )
        binding.includeTimeServingsSection.activityRecipeDetailsXmlServingsText.text =
            getString(
                R.string.resource_strings_recipe_servings_plural,
                viewModel.extraRecipeDetails?.servings
            )

        // other  //string repository
        binding.sourceNameClickableLink.text =
            viewModel.extraRecipeDetails?.sourceName ?: getString(R.string.source_name_if_null)

        //nutritional section //do przeniesienia do VM + stworzenie string repository
        binding.includeNutriSection.nutritionalTitle.text = getString(
            R.string.resource_strings_nutritional_title_singular,
            1
        )

    }

    private fun loadRecipeImage() {

        val recipeId = viewModel.extraRecipeDetails?.id
        val imageType = viewModel.extraRecipeDetails?.imageType

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