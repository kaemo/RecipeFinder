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

    private val errorDescriptionAdvance =
        savedStateHandle.get<String>("extraErrorMessage")
    private val errorDescriptionSimple = errorDecoder(errorDescriptionAdvance)

    private var isDevFunctionEnabled =
        getSharedPreferencesUseCase().getBoolean(IS_USER_A_DEVELOPER, false)

    private val _uiMessages = MutableLiveData<UiMessage>()
    val uiMessages: LiveData<UiMessage> = _uiMessages

    private val _errorDesc =
        MutableLiveData<String>(if (isDevFunctionEnabled) errorDescriptionAdvance else errorDescriptionSimple)
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
        _uiMessages.postValue(UiMessage.Toast(if (isDevFunctionEnabled) "You are now a developer!" else "You are no longer a developer"))
    }

    private fun updateErrorDescription() {
        _errorDesc.postValue(if (isDevFunctionEnabled) errorDescriptionAdvance else errorDescriptionSimple)
    }

    private fun errorDecoder(errorDescriptionAdvance: String?): String {
        errorDescriptionAdvance?.let {
            with(it) {
                return when {
                    contains("timeout") -> "It took too long to load recipes. It may be caused by a slow Internet connection. Check yor Internet connection and try again."
                    contains("no address associated", true) -> "There is a problem with connecting to the server. Check yor Internet connection and try again."
                    contains("failed to connect to api", true) -> "There is a problem with connecting to the server. Check yor Internet connection and try again."
                    else -> "Unknown error"
                }
            }
        } ?: return "null error description"
    }

}