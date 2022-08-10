package pl.kaemo.recipefinder.UI.MainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import pl.kaemo.recipefinder.R

class MainActivity : AppCompatActivity() {

    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.main_activity_mainText)
        val button = findViewById<TextView>(R.id.main_activity_button_plus)

        viewModel.greeting.observe(this) {
            textView.text = it
        }


        button.setOnClickListener {
            viewModel.onButtonClicked()
        }
    }
}