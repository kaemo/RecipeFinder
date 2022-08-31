package pl.kaemo.recipefinder.ui.mainActivity

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import pl.kaemo.recipefinder.R
import pl.kaemo.recipefinder.ui.util.AndroidLogger
import pl.kaemo.recipefinder.ui.util.IsKeyboardVisibleLiveData
import pl.kaemo.recipefinder.ui.util.KeyboardManager.hideKeyboard
import pl.kaemo.recipefinder.ui.util.LogcatLogger
import pl.kaemo.recipefinder.ui.util.NavigationManager.navigateToRecipesListActivity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val logger: LogcatLogger = AndroidLogger("TAG") // lub FileLogger()

    lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainRecyclerAdapter

    private lateinit var recyclerViewId: RecyclerView
    private lateinit var buttonAddId: ImageButton
    private lateinit var userInputId: TextView
    private lateinit var validationId: TextView
    private lateinit var buttonFindId: Button
    private lateinit var userGuideId: TextView
    private lateinit var mainTextId: TextView
    private lateinit var xmlLayoutId: ConstraintLayout
    private lateinit var loadingScreenId: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logger.logMessage("onCreate")

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        recyclerViewId = findViewById(R.id.activity_main_xml_recyclerview)
        buttonAddId = findViewById(R.id.activity_main_xml_imageButton_add)
        userInputId = findViewById(R.id.activity_main_xml_user_input)
        validationId = findViewById(R.id.activity_main_xml_validation)
        buttonFindId = findViewById(R.id.activity_main_xml_button_findmeal)
        userGuideId = findViewById(R.id.activity_main_xml_user_guide)
        mainTextId = findViewById(R.id.activity_main_xml_mainText)
        xmlLayoutId = findViewById(R.id.activity_main_xml_root)
        loadingScreenId = findViewById(R.id.activity_main_xml_loading_layout)

        initRecyclerView()
        observeIngredients()
        observeRecipes()

        userInputId.setOnFocusChangeListener { _, _ ->
            validationId.text = ""
            userGuideId.text = ""
        }

        IsKeyboardVisibleLiveData(xmlLayoutId).observe(this) { isVisible ->
            if (isVisible) {
                logger.logMessage("keyboard is visible")
                mainTextId.textSize = 40.0F
            } else {
                logger.logMessage("keyboard is invisible")
                mainTextId.textSize = 60.0F
            }
        }

        buttonAddId.setOnClickListener {
            logger.logMessage("Button ADD clicked")
            val validationStatus =
                IngredientNameValidation.validateUserInput(userInputId.text.toString())
            if (validationStatus is ValidationStatus.Error) {
                validationId.text = getString(validationStatus.message)
            } else {
                validationId.text = "" //clean the validation field
                viewModel.onIngredientAdded(userInputId.text.toString())
                userInputId.text = "" //clean the input
                userInputId.hint = getString(R.string.main_activity_inputSecondHintText)
            }
        }

        buttonFindId.setOnClickListener {
            if (viewModel.enoughIngredients()) {
                hideKeyboard()
                loadingScreenId.isVisible = true
                viewModel.onButtonSearchRecipesClicked()
            } else {
                validationId.text = getString(R.string.validation_noIngredients)
            }

        }

    }

    override fun onResume() {
        super.onResume()
        loadingScreenId.isVisible = false
    }

    private fun initRecyclerView() {
        logger.logMessage("initrecyclerview")
        recyclerViewId.layoutManager = LinearLayoutManager(this)
        adapter = MainRecyclerAdapter(::onItemDeleted)
        //adapter = MainRecyclerAdapter(viewModel::onIngredientDeleted) //bezpośrednie odwołanie z pominięciem metody
        recyclerViewId.adapter = adapter
    }

    private fun onItemDeleted(index: Int) {
        logger.logMessage("onItemDeleted index: $index")
        viewModel.onIngredientDeleted(index)
    }

    private fun observeIngredients() {
        viewModel.ingredients.observe(this) {
            logger.logMessage("List of ingredients: $it")
            adapter.update(it)
        }
    }

    private fun observeRecipes() {
        viewModel.recipes.observe(this) {
            navigateToRecipesListActivity(it)
        }
    }

}