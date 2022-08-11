package pl.kaemo.recipefinder.UI.MainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.kaemo.recipefinder.R

class MainActivity : AppCompatActivity() {

    //part of recycler view
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Recycler View
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.main_activity_recyclerview)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter = MainRecyclerAdapter()
        recyclerView.adapter = adapter
    }

}