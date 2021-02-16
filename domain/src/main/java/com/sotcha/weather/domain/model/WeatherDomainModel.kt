package com.sotcha.weather.domain.model

import java.util.*

/**
 * Forecast model for a specific day with hourly list
 */
data class WeatherDomainModel(
    val date: Date,
    val maxTemperature: Int,
    val minTemperature: Int,
    val hourly: List<HourlyDomainModel>
)
