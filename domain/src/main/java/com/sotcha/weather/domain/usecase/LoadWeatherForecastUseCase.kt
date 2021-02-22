package com.sotcha.weather.domain.usecase

import com.sotcha.weather.domain.model.CityDomainModel
import com.sotcha.weather.domain.repository.WeatherRepository
import javax.inject.Inject

/**
 * Use case which loads the weather data for a city
 *
 */
class LoadWeatherForecastUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {

    suspend operator fun invoke(city: CityDomainModel, loadHourly: Boolean = false) =
        weatherRepository.getLocalWeather(city, loadHourly)


}