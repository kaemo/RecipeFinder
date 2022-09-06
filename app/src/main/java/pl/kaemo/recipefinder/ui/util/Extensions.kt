package pl.kaemo.recipefinder.ui.util

import android.app.Activity
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

fun Activity.showUiMessage(uiMessage: UiMessage) {
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

fun Double.trimIfMoreDecimalThan(decimal: Int): Double {
    var numberString: String = this.toString()
    val indexOfDecimal: Int = numberString.indexOf('.') + 1
    return if (this % 1 > 0 && numberString.length - indexOfDecimal > decimal) {
        numberString = numberString.dropLast(numberString.length - indexOfDecimal - decimal)
        numberString.toDouble()
    } else {
        this
    }
}


