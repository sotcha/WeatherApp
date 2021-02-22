package com.sotcha.weather.data.repository

import com.sotcha.weather.data.api.WeatherRepoApiService
import com.sotcha.weather.data.mapper.WeatherForecastMapper
import com.sotcha.weather.domain.model.CityDomainModel
import com.sotcha.weather.domain.model.WeatherForecastDomainModel
import com.sotcha.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class WeatherRepositoryImpl
    (private val weatherRepoApiService: WeatherRepoApiService) : WeatherRepository {

    override suspend fun getLocalWeather(
        cityDomainModel: CityDomainModel, loadHourly: Boolean
    ): Flow<WeatherForecastDomainModel> {
        // The query term should have this format 48.834,2.394
        val query =
            formatLatLng(cityDomainModel.lat).plus(",").plus(formatLatLng(cityDomainModel.long))

        var data: WeatherForecastDomainModel? = null
        withContext(Dispatchers.IO) {
            val weather =
                if (loadHourly) weatherRepoApiService.weather(query, "5", "1")
                else weatherRepoApiService.weather(query, "0")

            data = WeatherForecastMapper.map(weather)
        }

        return flow { emit(data!!) }
    }

    private fun formatLatLng(value: Float): String = String.format("%.3f", value)
}
