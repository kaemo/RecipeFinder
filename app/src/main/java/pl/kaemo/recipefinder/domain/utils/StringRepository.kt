package pl.kaemo.recipefinder.domain.utils

import androidx.annotation.StringRes

interface StringRepository {
    fun getString(@StringRes id: Int): String
    fun getString(@StringRes id: Int, vararg formatArgs: Any): String
}