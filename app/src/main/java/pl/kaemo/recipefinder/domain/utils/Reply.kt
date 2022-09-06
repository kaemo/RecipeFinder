package pl.kaemo.recipefinder.domain.utils

sealed class Reply<T : Any> {
    class Error<T : Any>(val error: Throwable, val data: T? = null) : Reply<T>()
    class Success<T : Any>(val data: T) : Reply<T>()
}