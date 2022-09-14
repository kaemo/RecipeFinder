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

    private val _devStatus = MutableLiveData<Boolean>()
    val devStatus: LiveData<Boolean> = _devStatus

    var taps: Int = 0

    fun onImageClicked(isUserADeveloper: Boolean) {

        taps++
        logger.log(taps.toString())

        if (!isUserADeveloper && taps % 7 == 0) {
            val toast = UiMessage.Toast("You are now a developer!")
            _uiMessages.postValue(toast)
            _devStatus.postValue(true)
        } else if (isUserADeveloper) {
            val toast = UiMessage.Toast("No need, you are already a developer")
            _uiMessages.postValue(toast)
            _devStatus.postValue(true)
        }

    }
}