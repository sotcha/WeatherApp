package com.sotcha.weather.domain.model


data class LocalWeatherConfDomainModel(
    val cityLat: Float,
    val cityLong: Float,
    val weather: WeatherDomainModel
)
