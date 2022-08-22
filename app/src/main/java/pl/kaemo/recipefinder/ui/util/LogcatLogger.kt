package pl.kaemo.recipefinder.ui.util

import android.util.Log

interface LogcatLogger {
    fun logMessage(text: String)
}

class AndroidLogger(private val tag: String) : LogcatLogger {
    override fun logMessage(text: String) {
        Log.d(tag, text)
    }
}

class FileLogger : LogcatLogger {
    override fun logMessage(text: String) {
        //TO DO zapisywanie do pliku
    }
}