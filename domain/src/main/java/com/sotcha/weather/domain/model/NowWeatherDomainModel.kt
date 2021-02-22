package com.sotcha.weather.domain.model


data class NowWeatherDomainModel(
    val conditions: ConditionsDomailModel,
    val observationTime: String
)
