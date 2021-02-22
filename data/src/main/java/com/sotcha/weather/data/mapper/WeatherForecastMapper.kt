package com.sotcha.weather.data.mapper

import com.sotcha.weather.data.model.response.WeatherApiRepoResponse
import com.sotcha.weather.domain.model.WeatherForecastDomainModel

object WeatherForecastMapper {
    fun map(response: WeatherApiRepoResponse): WeatherForecastDomainModel =
        WeatherForecastDomainModel(
            current = CurrentWeatherMapper.map(response.data?.currentCondition?.firstOrNull()),
            weather = response.data?.weather?.map { DayWeatherMapper.map(it) })

}