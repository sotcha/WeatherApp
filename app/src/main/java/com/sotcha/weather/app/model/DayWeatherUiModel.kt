package com.sotcha.weather.app.model

data class DayWeatherUiModel(
    val hourHeaderUiModel: HourHeaderUiModel,
    val hours: List<HourItemUiModel>
)
