package com.sotcha.weather.domain.model

data class SearchLocationDomainModel(
    val id: Long = 0,
    val name: String,
    val region: String,
    val country: String,
    val lat: Float,
    val long: Float,
)