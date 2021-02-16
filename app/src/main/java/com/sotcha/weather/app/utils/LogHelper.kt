package com.sotcha.weather.app.utils

import android.util.Log

/**
 * Log helping utility for debugging purposes
 */
object LogHelper {
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

}