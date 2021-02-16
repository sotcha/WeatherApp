package com.sotcha.weather.domain.model


data class CurrentConditionDomainModel(
    val temperature: Int,
    val feelsLikeTemperature: Int,
    val windSpeed: Int,
    val pressure: Int,
    val humidity: Float, // Humidity in percentage
    val weatherIconUrl: String,
    val weatherDescription: String

)
