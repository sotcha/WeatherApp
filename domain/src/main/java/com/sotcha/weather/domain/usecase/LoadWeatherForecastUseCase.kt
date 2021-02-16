package com.sotcha.weather.domain.usecase

import com.sotcha.weather.domain.model.CityDomainModel
import com.sotcha.weather.domain.repository.WeatherRepository
import javax.inject.Inject

class LoadWeatherForecastUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    fun invoke(city: CityDomainModel) {
        weatherRepository.getLocalWeather(city)
    }


}