package pl.kaemo.recipefinder.ui.recipeDetailsActivity

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import pl.kaemo.recipefinder.R
import pl.kaemo.recipefinder.databinding.ActivityRecipeDetailsBinding
import pl.kaemo.recipefinder.ui.util.CustomLogger
import pl.kaemo.recipefinder.ui.util.LogcatLogger
import pl.kaemo.recipefinder.ui.util.showUiMessage

@AndroidEntryPoint
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

        setUpSpinner()

    }

    private fun setUpSpinner() {
        val spinner = findViewById<Spinner>(R.id.spinner_servings)
        val adapter = ArrayAdapter(
            this,
            R.layout.spinner_servings_item,
            R.id.spinner_text_servings,
            viewModel.defaultServingOptions
        )
        adapter.setDropDownViewResource(R.layout.spinner_servings_dropdown_item)
        spinner.adapter = adapter
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