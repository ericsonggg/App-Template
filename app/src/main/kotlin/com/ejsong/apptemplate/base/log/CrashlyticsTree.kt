package com.ejsong.apptemplate.base.log

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber
import javax.inject.Inject

/**
 * A [Timber] tree that:
 * - sends error level logs to Crashlytics as a non-fatal error
 * - sends info/warning level logs to Crashlytics as a log message
 */
class CrashlyticsTree @Inject constructor(
    private val crashlytics: FirebaseCrashlytics
) : Timber.DebugTree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        when (priority) {
            Log.ASSERT, Log.ERROR -> {
                // Create a non-fatal error
                val error = t ?: Throwable("$tag: $message")
                crashlytics.recordException(error)
            }
            Log.WARN, Log.INFO -> {
                // Create a log
                crashlytics.log("$tag: $message")
            }
        }
    }
}