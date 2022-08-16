package pl.kaemo.recipefinder.ui.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.kaemo.recipefinder.R
import pl.kaemo.recipefinder.ui.mainActivity.recyclerView.FakeDataBase
import pl.kaemo.recipefinder.ui.mainActivity.recyclerView.MainRecyclerAdapter

class MainActivity : AppCompatActivity() {

    //part of recycler view
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder>? = null

    private lateinit var recyclerViewId: RecyclerView
    private lateinit var buttonAddId: ImageButton
    private lateinit var userInputId: TextView
    private lateinit var validationId: TextView
    private lateinit var buttonFindId: Button
    private lateinit var userGuideId: TextView
    private lateinit var mainTextId: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewId = findViewById(R.id.activity_main_xml_recyclerview)
        buttonAddId = findViewById(R.id.activity_main_xml_imageButton_add)
        userInputId = findViewById(R.id.activity_main_xml_user_input)
        validationId = findViewById(R.id.activity_main_xml_validation)
        buttonFindId = findViewById(R.id.activity_main_xml_button_findmeal)
        userGuideId = findViewById(R.id.activity_main_xml_user_guide)
        mainTextId = findViewById(R.id.activity_main_xml_mainText)

        initRecyclerView()

        buttonFindId.setOnClickListener {
            Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show()
        }

        buttonAddId.setOnClickListener {
            if (userInputIsValid(userInputId.text.toString())) {
                addIngredient()
                userInputId.hint = getString(R.string.main_activity_inputSecondHintText)
            }
        }

        userInputId.setOnFocusChangeListener { _, _ ->
            userGuideId.text = ""
            mainTextId.textSize = 40.0F //nie znalazłem sposobu na wykrywanie pojawiania się samej klawiatury. Masz może jakiś pomysł jak nasłuchiwać wysuwanie się klawiatury?
        }

    }

    private fun initRecyclerView() {
        layoutManager = LinearLayoutManager(this)
        recyclerViewId.layoutManager = layoutManager
        adapter = MainRecyclerAdapter()
        recyclerViewId.adapter = adapter
    }

    private fun addIngredient() {
        val getUserInput: String = userInputId.text.toString()
        val listLastIndex = FakeDataBase.ingredientsList.size
        FakeDataBase.ingredientsList.add(listLastIndex, getUserInput)
        adapter?.notifyItemInserted(listLastIndex)
        userInputId.text = "" //clean the input
    }

    private fun userInputIsValid(userInput: String): Boolean {

        return if (userInput == "") {
            validationId.text = getString(R.string.validation_empty)
            false
        } else if (userInput.contains(" ")) {
            validationId.text = getString(R.string.validation_whitespaces)
            false
        } else if (userInput.contains("[0-9]".toRegex())) {
            validationId.text = getString(R.string.validation_digits)
            false
        } else if (userInput.contains("[!\"#\$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())) {
            validationId.text = getString(R.string.validation_specChar)
            false
        } else if (userInput.length == 1) {
            validationId.text = getString(R.string.validation_char)
            false
        } else if (userInput.length > 70) {
            validationId.text = getString(R.string.validation_long)
            false
        } else {
            validationId.text = ""
            true
        }
    }

}