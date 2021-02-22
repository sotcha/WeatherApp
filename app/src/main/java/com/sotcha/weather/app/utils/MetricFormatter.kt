package com.sotcha.weather.app.utils

/**
 * Formatting helper utility
 */
object MetricFormatter {
    fun formatTemperature(value: Int) = "$value°C"
    fun formatDistance(value: Int) = "$value km"
    fun formatPressure(value: Int) = "$value hPa"
    fun formatSpeed(value: Int) = "$value km/h"
}