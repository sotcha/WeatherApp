package com.sotcha.weather.domain.model


class ConditionsDomailModel(
    val temperature: Int,
    val feelsLikeTemperature: Int,
    val windSpeed: Int,
    val pressure: Int,
    val visibility: Int,
    val humidity: Int, // Humidity in percentage
    val weatherIconUrl: String,
    val weatherDescription: String,
)