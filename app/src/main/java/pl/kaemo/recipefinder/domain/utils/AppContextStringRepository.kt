package pl.kaemo.recipefinder.domain.utils

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppContextStringRepository @Inject constructor(
    @ApplicationContext private val context: Context
) : StringRepository {
    override fun getString(@StringRes id: Int): String {
        return context.getString(id)
    }

    override fun getString(@StringRes id: Int, vararg formatArgs: Any): String {
        return context.getString(id, *formatArgs)
    }
}