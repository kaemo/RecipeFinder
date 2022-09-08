package pl.kaemo.recipefinder.ui.errorScreenActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.kaemo.recipefinder.ui.util.CustomLogger
import pl.kaemo.recipefinder.ui.util.LogcatLogger
import pl.kaemo.recipefinder.ui.util.UiMessage

class ErrorScreenViewModel : ViewModel() {

    private val logger: CustomLogger = LogcatLogger("ErrorScreenVM") // lub FileLogger()

    private val _uiMessages = MutableLiveData<UiMessage>()
    val uiMessages: LiveData<UiMessage> = _uiMessages

    var taps: Int = 0

    fun onImageClicked(isUserADeveloper: Boolean): Boolean {

        taps++
        logger.log(taps.toString())

        return if (!isUserADeveloper && taps % 7 == 0) {
            val toast = UiMessage.Toast("You are now a developer!")
            _uiMessages.postValue(toast)
            true
        } else if (isUserADeveloper) {
            val toast = UiMessage.Toast("No need, you are already a developer")
            _uiMessages.postValue(toast)
            true
        } else {
            false
        }

    }
}