package com.sotcha.weather.data.model.response

data class SearchLocationRepoResponse(
    val areaName: List<ValueRepoResponse>,
    val country: List<ValueRepoResponse>,
    val region: List<ValueRepoResponse>,
    val latitude: String,
    val longitude: String,
    val population: String
)
