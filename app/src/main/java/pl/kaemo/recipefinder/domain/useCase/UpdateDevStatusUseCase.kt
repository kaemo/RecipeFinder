package pl.kaemo.recipefinder.domain.useCase

import android.content.SharedPreferences
import pl.kaemo.recipefinder.ui.util.IS_USER_A_DEVELOPER
import javax.inject.Inject

class UpdateDevStatusUseCase @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    operator fun invoke(isDevFunctionEnabled: Boolean): Unit {
        sharedPreferences
            .edit()
            .putBoolean(IS_USER_A_DEVELOPER, isDevFunctionEnabled)
            .apply()
    }
}