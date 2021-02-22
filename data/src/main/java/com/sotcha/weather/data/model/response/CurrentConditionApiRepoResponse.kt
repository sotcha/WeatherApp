package com.sotcha.weather.data.model.response

import com.google.gson.annotations.SerializedName

data class CurrentConditionApiRepoResponse(
    @field:SerializedName("observation_time") val observation_time: String,
    @field:SerializedName("temp_C") val temp_C: String,
    @field:SerializedName("windspeedKmph") val windspeedKmph: String,
    @field:SerializedName("humidity") val humidity: String,
    @field:SerializedName("visibility") val visibility: String,
    @field:SerializedName("pressure") val pressure: String,
    @field:SerializedName("FeelsLikeC") val FeelsLikeC: String,
    @field:SerializedName("weatherIconUrl") val weatherIconUrl: List<ValueRepoResponse>,
    @field:SerializedName("weatherDesc") val weatherDesc: List<ValueRepoResponse>
)
