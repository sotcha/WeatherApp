package com.sotcha.weather.domain.model

/**
 * Represents the weather forecast with current and next days
 *
 * @property current
 * @property weather
 */
data class WeatherForecastDomainModel(
    val current: NowWeatherDomainModel?,
    val weather: List<WeatherDomainModel>?
)
