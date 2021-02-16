package com.sotcha.weather.domain.model

/**
 * Hourly forecast model for a specific day
 *
 */
data class HourlyDomainModel(
    val time: String,
    val temperature: Int,
    val feelsLikeTemperature: Int,
    val windSpeed: Int,
    val pressure: Int,
    val humidity: Float, // Humidity in percentage
    val weatherIconUrl: String,
    val weatherDescription: String
)