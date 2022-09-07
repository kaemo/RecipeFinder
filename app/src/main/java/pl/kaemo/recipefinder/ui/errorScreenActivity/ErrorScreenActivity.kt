package pl.kaemo.recipefinder.ui.errorScreenActivity

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import pl.kaemo.recipefinder.R
import kotlin.random.Random

class ErrorScreenActivity : AppCompatActivity() {

    private lateinit var imageError: ImageView
    private lateinit var returnButton: Button
    private lateinit var errorText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error_screen)

        imageError = findViewById(R.id.imageView)
        returnButton = findViewById(R.id.errorButton)
        errorText = findViewById(R.id.errorTextView)

        when (Random.nextInt(6)) {
            1 -> imageError.setImageResource(R.drawable.error1)
            2 -> imageError.setImageResource(R.drawable.error2)
            3 -> imageError.setImageResource(R.drawable.error3)
            4 -> imageError.setImageResource(R.drawable.error4)
            5 -> imageError.setImageResource(R.drawable.error5)
            else -> imageError.setImageResource(R.drawable.error6)
        }

        returnButton.setOnClickListener {
            onBackPressed()
        }

        intent.getStringExtra("extraErrorMessage")?.let {
            errorText.text = it
        }

    }
}