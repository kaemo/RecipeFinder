package pl.kaemo.recipefinder.ui.recipesListActivity

import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.kaemo.recipefinder.R
import pl.kaemo.recipefinder.ui.util.UiMessage

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

        recyclerViewId = findViewById(R.id.activity_recipes_list_xml_recyclerview)
        tooltipId = findViewById(R.id.activity_recipes_list_xml_tooltip)
        sortButtonId = findViewById(R.id.activity_recipes_list_xml_sort_button)
        moreButtonId = findViewById(R.id.activity_recipes_list_xml_more_button)

        initRecyclerview()
        observeUiMessages()

        tooltipId.setOnClickListener {
            viewModel.onTooltipClicked()
        }

        sortButtonId.setOnClickListener {
            viewModel.onSortButtonClicked()
        }

        moreButtonId.setOnClickListener {
            viewModel.onMoreButtonClicked()
        }

    }

    private fun initRecyclerview() {
        recyclerViewId.layoutManager = LinearLayoutManager(this)
        adapter = RecipesListAdapter()
        recyclerViewId.adapter = adapter
    }

    private fun observeUiMessages() {
        viewModel.uiMessages.observe(this) { uiMessage ->
            when (uiMessage) {
                is UiMessage.Dialog -> {
                    val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                    builder.setIcon(uiMessage.icon)
                    builder.setTitle(uiMessage.title)
                    builder.setMessage(uiMessage.message)
                    builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }

                    val alertDialog: AlertDialog = builder.create()
                    alertDialog.show()
                }
                is UiMessage.Toast -> {
                    Toast.makeText(this, uiMessage.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}