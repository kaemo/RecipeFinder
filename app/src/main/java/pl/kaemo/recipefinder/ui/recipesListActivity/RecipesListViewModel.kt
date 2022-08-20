package pl.kaemo.recipefinder.ui.recipesListActivity

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import pl.kaemo.recipefinder.R

class RecipesListViewModel: ViewModel() {

    fun showDialog(context: Context) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setIcon(R.drawable.ic_baseline_info_24)
        builder.setTitle("Your plan: FREE")
        builder.setMessage(R.string.recipes_list_activity_dialog_message)
        builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

    fun toast(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}