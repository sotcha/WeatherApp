package com.sotcha.weather.app.utils

import android.util.Log

/**
 * Log helping utility for debugging purposes
 */
object DebugLogHelper {
    /**
     * Enable or not logging
     */
    var enable = false

    /**
     * Set tag for the logging
     */
    var tag = "LogHelper"

    fun d(s: String) {
        if (enable) Log.d(tag, s)
    }

    fun e(msg: String, error: Throwable) {
        if (enable) Log.e(tag, msg, error)
    }

}