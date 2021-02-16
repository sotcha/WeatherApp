package com.sotcha.weather.domain.repository

import com.sotcha.weather.domain.model.CityDomainModel
import com.sotcha.weather.domain.model.WeatherForecastDomainModel


interface WeatherRepository {
    fun getLocalWeather(cityDomainModel: CityDomainModel): WeatherForecastDomainModel
}