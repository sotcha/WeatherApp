package com.sotcha.weather.app.model

data class SearchLocationUiModel(
    val id: Long = 0,
    val name: String,
    val region: String,
    val country: String,
    val lat: Float,
    val long: Float,

    /**
     * Declares if the the item is saved
     */
    var isSaved: Boolean = false, // Set as default value
)