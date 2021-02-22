package com.sotcha.weather.domain.repository

import com.sotcha.weather.domain.model.CityDomainModel
import com.sotcha.weather.domain.model.WeatherForecastDomainModel
import kotlinx.coroutines.flow.Flow

/**
 * Repository which provides weather data
 *
 */
interface WeatherRepository {

    suspend fun getLocalWeather(cityDomainModel: CityDomainModel, loadHourly: Boolean): Flow<WeatherForecastDomainModel>

}