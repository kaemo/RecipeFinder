package pl.kaemo.recipefinder.ui.util

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

fun AlertDialog.Builder.showDialog(icon: Int, title: Int, message: Int) {
    this.setIcon(icon)
    this.setTitle(title)
    this.setMessage(message)

    this.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }

    val alertDialog: AlertDialog = this.create()
    alertDialog.show()
}

fun String.showToast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}