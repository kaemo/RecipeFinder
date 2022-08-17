package pl.kaemo.recipefinder.ui.util

import android.view.View
import android.view.ViewTreeObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


class IsKeyboardVisibleLiveData(
    private val root: View,
    private val treshold: Float = 0.3f
) : LiveData<Boolean>() {

    private val listener = ViewTreeObserver.OnGlobalLayoutListener {
        val rootHeight = root.height.toFloat()
        val screenHeight = root.resources.displayMetrics.heightPixels.toFloat()
        val isKeyboardProbablyOpen = rootHeight / screenHeight < 1 - treshold
        if (isKeyboardProbablyOpen != value) {
            value = isKeyboardProbablyOpen
        }
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<in Boolean>) {
        super.observe(owner, observer)
        onObserversUpdated()
    }

    override fun removeObserver(observer: Observer<in Boolean>) {
        super.removeObserver(observer)
        onObserversUpdated()
    }

    private fun onObserversUpdated() {
        if (hasObservers()) {
            root.viewTreeObserver.addOnGlobalLayoutListener(listener)
        } else {
            root.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }
}