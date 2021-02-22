package com.sotcha.weather.domain.usecase

import com.sotcha.weather.domain.repository.LocationsRepository
import javax.inject.Inject

/**
 * Use case which loads saved locations from storage
 */
class LoadLocationsUseCase @Inject constructor(
    private val locationsRepo: LocationsRepository
) {

    suspend operator fun invoke() = locationsRepo.loadSavedLocations()

}