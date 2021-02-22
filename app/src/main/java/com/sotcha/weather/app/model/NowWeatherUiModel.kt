package com.sotcha.weather.app.model

import android.net.Uri

data class NowWeatherUiModel(
    val temperature: Int,
    val feelsLikeTemperature: Int,
    val weatherIconUri: Uri?,
    val weatherDescription: String,
    val conditions: List<ConditionUiModel>
)
