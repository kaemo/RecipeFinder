package pl.kaemo.recipefinder.UI.MainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.kaemo.recipefinder.R

class MainActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder>? = null

    //val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Recycler View

        val recyclerView = findViewById<RecyclerView>(R.id.main_activity_recyclerview)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter = MainRecyclerAdapter()
        recyclerView.adapter = adapter
    }
}