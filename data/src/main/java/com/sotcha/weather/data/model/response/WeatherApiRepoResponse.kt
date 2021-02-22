package com.sotcha.weather.data.model.response

data class WeatherApiRepoResponse(
     val data: WeatherDataApiRepoResponse?

) {
    /**
     * Return if an error exists under error api response
     */
//    fun hasError(): Boolean = data?.error?.isNotEmpty() ?: false
}