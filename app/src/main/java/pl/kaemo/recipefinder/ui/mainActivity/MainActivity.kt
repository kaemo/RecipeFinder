package pl.kaemo.recipefinder.ui.mainActivity

import android.annotation.SuppressLint
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

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()

        findViewById<Button>(R.id.main_activity_button_findmeal).setOnClickListener {
            Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show()
        }

        val inputId = findViewById<TextView>(R.id.textInputEditText)

        findViewById<ImageButton>(R.id.main_activity_imageButton_add).setOnClickListener {
            if (userInputIsValid(inputId.text.toString())) {
                addIngredient(inputId)
                inputId.hint = getString(R.string.main_activity_inputSecondHintText)
            }
        }

        findViewById<TextView>(R.id.textInputEditText).setOnFocusChangeListener { _, _ ->
            findViewById<TextView>(R.id.InfoText).text = ""
            findViewById<TextView>(R.id.main_activity_mainText).textSize = 40.0F //nie znalazłem sposobu na wykrywanie pojawiania się samej klawiatury. Masz może jakiś pomysł jak nasłuchiwać wysuwanie się klawiatury?
        }

    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.main_activity_recyclerview)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter = MainRecyclerAdapter()
        recyclerView.adapter = adapter
    }

    @SuppressLint("CutPasteId")
    private fun addIngredient(inputId: TextView) {
        val getUserInput: String = inputId.text.toString()
        val listLastIndex = FakeDataBase.ingredientsList.size
        FakeDataBase.ingredientsList.add(listLastIndex, getUserInput)
        adapter?.notifyItemInserted(listLastIndex)
        inputId.text = "" //clean the input
    }

    private fun userInputIsValid(userInput: String): Boolean {

        val validationId = findViewById<TextView>(R.id.main_activity_validation_text)

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