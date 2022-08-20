package pl.kaemo.recipefinder.ui.recipesListActivity

import android.app.AlertDialog
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.kaemo.recipefinder.R

class RecipesListActivity : AppCompatActivity() {

    lateinit var viewModel: RecipesListViewModel

    private lateinit var adapter: RecipesListAdapter

    private lateinit var recyclerViewId: RecyclerView
    private lateinit var tooltipId: ImageButton
    private lateinit var sortButtonId: ImageButton
    private lateinit var moreButtonId: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes_list)

        viewModel = ViewModelProvider(this).get(RecipesListViewModel::class.java)

        recyclerViewId = findViewById<RecyclerView>(R.id.activity_recipes_list_xml_recyclerview)
        tooltipId = findViewById(R.id.activity_recipes_list_xml_tooltip)
        sortButtonId = findViewById(R.id.activity_recipes_list_xml_sort_button)
        moreButtonId = findViewById(R.id.activity_recipes_list_xml_more_button)

        initRecyclerview()

        tooltipId.setOnClickListener {
            showDialog()
        }

        sortButtonId.setOnClickListener {
            Toast.makeText(this, "Sorting not implemented yet!", Toast.LENGTH_SHORT).show()
        }

        moreButtonId.setOnClickListener {
            Toast.makeText(this, "Settings not implemented yet!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun initRecyclerview(){
        recyclerViewId.layoutManager = LinearLayoutManager(this)
        adapter = RecipesListAdapter()
        recyclerViewId.adapter = adapter
    }

    private fun showDialog() {
        val builder:AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setIcon(R.drawable.ic_baseline_info_24)
        builder.setTitle("Your plan: FREE")
        builder.setMessage(R.string.recipes_list_activity_dialog_message)
        builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }
}