package pl.kaemo.recipefinder.ui.errorScreenActivity

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import pl.kaemo.recipefinder.R

class ErrorScreenActivity : AppCompatActivity() {

    private lateinit var returnButton: Button
    private lateinit var errorText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error_screen)

        returnButton = findViewById(R.id.errorButton)
        errorText = findViewById(R.id.errorTextView)

        intent.getStringExtra("extraErrorMessage")?.let {
            errorText.text = it
        }

        returnButton.setOnClickListener {
            onBackPressed()
        }

    }
}