package pl.kaemo.recipefinder.ui.errorScreenActivity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import pl.kaemo.recipefinder.R
import pl.kaemo.recipefinder.ui.util.*
import kotlin.random.Random

class ErrorScreenActivity : AppCompatActivity() {

    private val logger: CustomLogger = LogcatLogger("ErrorScreenActivity") // lub FileLogger()
    lateinit var viewModel: ErrorScreenViewModel
    lateinit var sharedPrefs: SharedPreferences

    private lateinit var imageError: ImageView
    private lateinit var returnButton: Button
    private lateinit var errorText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error_screen)

        viewModel = ViewModelProvider(this).get(ErrorScreenViewModel::class.java)
        sharedPrefs = getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE)

        imageError = findViewById(R.id.imageView)
        returnButton = findViewById(R.id.errorButton)
        errorText = findViewById(R.id.errorTextView)

        observeUiMessages()
        observeDevStatus()

        when (Random.nextInt(6)) {
            1 -> imageError.setImageResource(R.drawable.error1)
            2 -> imageError.setImageResource(R.drawable.error2)
            3 -> imageError.setImageResource(R.drawable.error3)
            4 -> imageError.setImageResource(R.drawable.error4)
            5 -> imageError.setImageResource(R.drawable.error5)
            else -> imageError.setImageResource(R.drawable.error6)
        }

        imageError.setOnClickListener {
            viewModel.onImageClicked(sharedPrefs.getBoolean(IS_USER_A_DEVELOPER, false))
        }

        returnButton.setOnClickListener {
            onBackPressed()
        }

        if (sharedPrefs.getBoolean(IS_USER_A_DEVELOPER, false)) {
            intent.getStringExtra("extraErrorMessage")?.let {
                errorText.text = it
            }
        }

    }

    private fun observeUiMessages() {
        viewModel.uiMessages.observe(this) { uiMessage ->
            showUiMessage(uiMessage)
        }
    }

    private fun observeDevStatus() {
        viewModel.devStatus.observe(this) { devStatusTrue ->
            sharedPrefs.edit().putBoolean(IS_USER_A_DEVELOPER, devStatusTrue).apply()
            intent.getStringExtra("extraErrorMessage")?.let {
                errorText.text = it
            }
        }
    }

}