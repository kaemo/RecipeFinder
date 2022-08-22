package pl.kaemo.recipefinder.ui.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class UiMessage {
    class Toast(val message: String) : UiMessage()
    class Dialog(
        @DrawableRes val icon: Int,
        @StringRes val title: Int,
        @StringRes val message: Int
    ) : UiMessage()
}