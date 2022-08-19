package pl.kaemo.recipefinder.ui.mainActivity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.kaemo.recipefinder.R
import pl.kaemo.recipefinder.ui.util.IsKeyboardVisibleLiveData

class MainActivity : AppCompatActivity() {

    private val logger: Logger = AndroidLogger("TAG") // lub FileLogger()

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

        initRecyclerView()

        observeIngredients()

        buttonFindId.setOnClickListener {
            Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show()
        }

        buttonAddId.setOnClickListener {
            logger.logMessage("Button ADD clicked")
            val validationStatus = IngredientNameValidation.validateUserInput(userInputId.text.toString())
            if (validationStatus is ValidationStatus.Error){
                validationId.text = getString(validationStatus.message)
            } else {
                validationId.text = "" //clean the validation field
                viewModel.onIngredientAdded(userInputId.text.toString())
                userInputId.text = "" //clean the input
                userInputId.hint = getString(R.string.main_activity_inputSecondHintText)
            }
        }

        userInputId.setOnFocusChangeListener { _, _ ->
            userGuideId.text = ""
        }

        IsKeyboardVisibleLiveData(xmlLayoutId).observe(this) {
            if (it) {
                logger.logMessage("keyboard is visible")
                mainTextId.textSize = 40.0F
            } else {
                logger.logMessage("keyboard is invisible")
                mainTextId.textSize = 60.0F
            }
        }

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
            Log.d("TAG", "it: $it")
            adapter.update(it)
        }
    }
}

interface Logger{
    fun logMessage(text: String)
}

class AndroidLogger(private val tag: String): Logger {
    override fun logMessage(text: String) {
        Log.d(tag, text)
    }
}

class FileLogger: Logger {
    override fun logMessage(text: String) {
        //TO DO zapisywanie do pliku
    }
}











