package com.sotcha.weather.app.di

import com.sotcha.weather.data.api.WeatherRepoApiService
import com.sotcha.weather.data.repository.WeatherRepositoryImpl
import com.sotcha.weather.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class WeatherRepoModule {

    @Provides
    fun provideWeatherRepository(weatherRepoApiService: WeatherRepoApiService): WeatherRepository {
        return WeatherRepositoryImpl(weatherRepoApiService)
    }

}