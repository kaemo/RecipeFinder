package pl.kaemo.recipefinder.ui.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.kaemo.recipefinder.R
import pl.kaemo.recipefinder.ui.mainActivity.recyclerView.MainRecyclerAdapter

class MainActivity : AppCompatActivity() {

    //part of recycler view
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Recycler View
        initRecyclerView()

        findViewById<Button>(R.id.main_activity_button_findmeal).setOnClickListener {
            Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show()
        }

        findViewById<ImageButton>(R.id.main_activity_imageButton_add).setOnClickListener {
            Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.main_activity_recyclerview)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter = MainRecyclerAdapter()
        recyclerView.adapter = adapter
    }

}