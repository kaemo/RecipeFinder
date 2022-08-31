package pl.kaemo.recipefinder.ui.recipesListActivity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import pl.kaemo.recipefinder.R
import pl.kaemo.recipefinder.domain.model.RecipePreview
import pl.kaemo.recipefinder.ui.util.*

@AndroidEntryPoint
class RecipesListActivity : AppCompatActivity() {

    private val logger: LogcatLogger = AndroidLogger("TAG") // lub FileLogger()

    lateinit var viewModel: RecipesListViewModel
    private lateinit var adapter: RecipesListAdapter
    lateinit var sharedPrefs: SharedPreferences

    private lateinit var recyclerViewId: RecyclerView
    private lateinit var tooltipId: ImageButton
    private lateinit var sortButtonId: ImageButton
    private lateinit var moreButtonId: ImageButton
    private lateinit var pointsLeftId: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes_list)

        viewModel = ViewModelProvider(this).get(RecipesListViewModel::class.java)
        sharedPrefs = getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE)

        recyclerViewId = findViewById(R.id.activity_recipes_list_xml_recyclerview)
        tooltipId = findViewById(R.id.activity_recipes_list_xml_tooltip)
        sortButtonId = findViewById(R.id.activity_recipes_list_xml_sort_button)
        moreButtonId = findViewById(R.id.activity_recipes_list_xml_more_button)
        pointsLeftId = findViewById(R.id.activity_recipes_list_xml_points_left)

        //recipes transferred form Mainctivity
        intent.getParcelableArrayListExtra<RecipePreview>("extraRecipesList")?.let {
            viewModel.onRecipesListActivityCreated(it)
        }

        updatePointsLeftSection()
        initRecyclerview()
        observeUiMessages()
        observeRecipes()

        tooltipId.setOnClickListener {
            viewModel.onTooltipClicked()
        }

        sortButtonId.setOnClickListener {
            viewModel.onSortButtonClicked()
        }

        moreButtonId.setOnClickListener {
            viewModel.onMoreButtonClicked()
        }

    }

    private fun updatePointsLeftSection() {
        pointsLeftId.text = getString(
            R.string.recipes_list_activity_quota_left,
            sharedPrefs.getFloat(SHARED_PREF_QUOTALEFT_KEY, -1F)
        )
    }

    private fun initRecyclerview() {
        recyclerViewId.layoutManager = LinearLayoutManager(this)
        adapter = RecipesListAdapter(::onItemClicked)
        recyclerViewId.adapter = adapter
    }

    private fun onItemClicked(index: Int) {
        logger.logMessage("onItemClicked index: $index")
        viewModel.onRecipeClicked(index)
    }

    private fun observeUiMessages() {
        viewModel.uiMessages.observe(this) { uiMessage ->
            showUiMessage(uiMessage)
        }
    }

    private fun observeRecipes() {
        viewModel.recipes.observe(this) {
            adapter.update(it)
        }
    }
}