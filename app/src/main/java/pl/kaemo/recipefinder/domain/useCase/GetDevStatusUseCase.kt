package pl.kaemo.recipefinder.domain.useCase

import android.content.SharedPreferences
import pl.kaemo.recipefinder.ui.util.IS_USER_A_DEVELOPER
import javax.inject.Inject

class GetDevStatusUseCase @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    operator fun invoke(): Boolean {
        return sharedPreferences.getBoolean(IS_USER_A_DEVELOPER, false)
    }
}