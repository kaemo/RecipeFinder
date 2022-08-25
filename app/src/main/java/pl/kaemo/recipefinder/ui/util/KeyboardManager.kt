package pl.kaemo.recipefinder.ui.util

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

object KeyboardManager {

    fun Activity.hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val inputMethodManager = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}