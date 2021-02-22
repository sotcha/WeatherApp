package com.sotcha.weather.data.mapper

import com.sotcha.weather.data.model.response.CurrentConditionApiRepoResponse
import com.sotcha.weather.data.model.response.HourlyApiRepoResponse
import com.sotcha.weather.data.model.response.ValueRepoResponse
import com.sotcha.weather.domain.model.ConditionsDomailModel

object ConditonMapper {
    fun map(response: CurrentConditionApiRepoResponse): ConditionsDomailModel {
        return ConditionsDomailModel(
            temperature = response.temp_C.toIntOrNull() ?: 0,
            feelsLikeTemperature = response.FeelsLikeC.toIntOrNull() ?: 0,
            windSpeed = response.windspeedKmph.toIntOrNull() ?: 0,
            pressure = response.pressure.toIntOrNull() ?: 0,
            visibility = response.visibility.toIntOrNull() ?: 0,
            weatherIconUrl = getValuefromList(response.weatherIconUrl),
            weatherDescription = getValuefromList(response.weatherDesc),
            humidity = response.humidity.toIntOrNull() ?: 0,
        )
    }


    fun map(response: HourlyApiRepoResponse): ConditionsDomailModel {
        return ConditionsDomailModel(
            temperature = response.tempC.toIntOrNull() ?: 0,
            feelsLikeTemperature = response.FeelsLikeC.toIntOrNull() ?: 0,
            windSpeed = response.windspeedKmph.toIntOrNull() ?: 0,
            pressure = response.pressure.toIntOrNull() ?: 0,
            visibility = response.visibility.toIntOrNull() ?: 0,
            weatherIconUrl = getValuefromList(response.weatherIconUrl),
            weatherDescription = getValuefromList(response.weatherDesc),
            humidity = response.humidity.toIntOrNull() ?: 0,
        )
    }









    private fun getValuefromList(weatherIconUrl: List<ValueRepoResponse>): String =
        weatherIconUrl.firstOrNull()?.value ?: ""

}