package pl.kaemo.recipefinder.domain.useCase

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import pl.kaemo.recipefinder.ui.util.SHARED_PREFS_KEY
import javax.inject.Inject

class GetSharedPreferencesUseCase @Inject constructor(
    @ApplicationContext private val context: Context
) {
    operator fun invoke(): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE)
    }
}