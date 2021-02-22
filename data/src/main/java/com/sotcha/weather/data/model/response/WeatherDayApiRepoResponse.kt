package com.sotcha.weather.data.model.response

import com.google.gson.annotations.SerializedName

data class WeatherDayApiRepoResponse(
    @field:SerializedName("date") val date: String,
    @field:SerializedName("maxtempC") val maxtempC: String,
    @field:SerializedName("mintempC") val mintempC: String,
    @field:SerializedName("avgtempC") val avgtempC: String,
    @field:SerializedName("hourly") val hourly: List<HourlyApiRepoResponse>
)
