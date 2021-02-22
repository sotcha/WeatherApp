package com.sotcha.weather.app.di

import com.sotcha.weather.data.api.WeatherRepoApiService
import com.sotcha.weather.data.db.SearchLocationDao
import com.sotcha.weather.data.repository.LocationsRepositoryImpl
import com.sotcha.weather.data.utils.SharedPrefsHelper
import com.sotcha.weather.domain.repository.LocationsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class LocationsRepositoryModule {

    @Provides
    fun provideLocationsRepository(
        weatherRepoApiService: WeatherRepoApiService,
        searchLocationDao: SearchLocationDao,
        sharedPrefsHelper: SharedPrefsHelper
    ): LocationsRepository {
        return LocationsRepositoryImpl(weatherRepoApiService, searchLocationDao, sharedPrefsHelper)
    }

}