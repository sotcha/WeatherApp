package com.sotcha.weather.data.model.response

import com.google.gson.annotations.SerializedName

data class WeatherDataApiRepoResponse(
    @field:SerializedName("current_condition") val currentCondition: List<CurrentConditionApiRepoResponse>?,
    @field:SerializedName("weather") val weather: List<WeatherDayApiRepoResponse>?
)