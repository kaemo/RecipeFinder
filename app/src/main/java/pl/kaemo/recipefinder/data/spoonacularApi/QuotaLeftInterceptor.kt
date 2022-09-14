package pl.kaemo.recipefinder.data.spoonacularApi

import android.content.Context
import android.util.Log
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response
import pl.kaemo.recipefinder.ui.util.SHARED_PREFS_KEY
import pl.kaemo.recipefinder.ui.util.SHARED_PREF_QUOTALEFT_KEY
import javax.inject.Inject

class QuotaLeftInterceptor @Inject constructor(val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val quota = getQuota(response.headers)
        val sharedPrefs = context.getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE)
        quota?.let {
            sharedPrefs.edit().putFloat(SHARED_PREF_QUOTALEFT_KEY, it).apply()
        }
        return response
    }
}

private fun getQuota(header: Headers): Float? {
    val quotaLeft = header.firstOrNull { it.first == "x-api-quota-left" }?.second
    Log.d("quota left:", "$quotaLeft")
    return quotaLeft?.toFloat()
}
