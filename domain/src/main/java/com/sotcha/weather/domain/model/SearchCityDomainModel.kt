package com.sotcha.weather.domain.model


/**
 * Represents a city as a Search result
 */
data class SearchCityDomainModel(
    val name: String,
    val country: String,
    val region: String,
    val lat: Float,
    val long: Float,
)
