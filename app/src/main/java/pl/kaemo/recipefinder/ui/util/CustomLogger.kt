package pl.kaemo.recipefinder.ui.util

import android.util.Log

interface CustomLogger {
    fun log(text: String)
}

class LogcatLogger(private val placeInCode: String) : CustomLogger {
    override fun log(message: String) {
        Log.d("CL: $placeInCode", message)
    }
}

class FileLogger : CustomLogger {
    override fun log(text: String) {
        //TODO zapisywanie do pliku
    }
}