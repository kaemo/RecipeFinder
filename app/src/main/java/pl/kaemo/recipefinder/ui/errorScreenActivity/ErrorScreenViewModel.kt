package pl.kaemo.recipefinder.ui.errorScreenActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.kaemo.recipefinder.domain.useCase.GetSharedPreferencesUseCase
import pl.kaemo.recipefinder.ui.util.CustomLogger
import pl.kaemo.recipefinder.ui.util.IS_USER_A_DEVELOPER
import pl.kaemo.recipefinder.ui.util.LogcatLogger
import pl.kaemo.recipefinder.ui.util.UiMessage
import javax.inject.Inject

@HiltViewModel
class ErrorScreenViewModel @Inject constructor(
    val getSharedPreferencesUseCase: GetSharedPreferencesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val logger: CustomLogger = LogcatLogger("ErrorScreenVM") // lub FileLogger()

    private val errorDescription = savedStateHandle.get<String>("extraErrorMessage") //DS: key used in NavManager
    private var isDevFunctionEnabled = getSharedPreferencesUseCase().getBoolean(IS_USER_A_DEVELOPER, false)

    private val _uiMessages = MutableLiveData<UiMessage>()
    val uiMessages: LiveData<UiMessage> = _uiMessages

    private val _errorDesc = MutableLiveData<String>(if (isDevFunctionEnabled) errorDescription else "")
    val errorDesc: LiveData<String> = _errorDesc

    private var taps: Int = 0
    private var lastClickTimestamp = 0L
    private val clicksDiff = 1000L
    private val tapsToToggleFunction = 7

    fun onImageClicked() {
        toggleDevStatus()
    }

    private fun toggleDevStatus() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTimestamp < clicksDiff) {
            taps++
            logger.log("Taps count: $taps")
        } else {
            taps = 1
            logger.log("Taps count set to: $taps")
        }
        lastClickTimestamp = currentTime

        if (taps >= tapsToToggleFunction) {
            taps = 0
            updateDevFunctionStatus()
            updateErrorDescription()
        }
    }

    private fun updateDevFunctionStatus() {
        val sharedPrefs = getSharedPreferencesUseCase()
        isDevFunctionEnabled = isDevFunctionEnabled.not()
        sharedPrefs.edit().putBoolean(IS_USER_A_DEVELOPER, isDevFunctionEnabled).apply()
        _uiMessages.postValue(UiMessage.Toast("Status changed to: $isDevFunctionEnabled"))
    }

    private fun updateErrorDescription() {
        _errorDesc.postValue(if (isDevFunctionEnabled) errorDescription else "")
    }

}