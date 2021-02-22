package com.sotcha.weather.data.model.response

import com.google.gson.annotations.SerializedName

data class HourlyApiRepoResponse(
    @field:SerializedName("time") val time: String,
    @field:SerializedName("tempC") val tempC: String,
    @field:SerializedName("windspeedKmph") val windspeedKmph: String,
    @field:SerializedName("humidity") val humidity: String,
    @field:SerializedName("visibility") val visibility: String,
    @field:SerializedName("pressure") val pressure: String,
    @field:SerializedName("FeelsLikeC") val FeelsLikeC: String,
    @field:SerializedName("weatherIconUrl") val weatherIconUrl: List<ValueRepoResponse>,
    @field:SerializedName("weatherDesc") val weatherDesc: List<ValueRepoResponse>,
)
