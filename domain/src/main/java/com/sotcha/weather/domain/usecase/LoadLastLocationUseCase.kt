package com.sotcha.weather.domain.usecase

import com.sotcha.weather.domain.repository.LocationsRepository
import javax.inject.Inject

/**
 * Use case which loads last location from storage
 */
class LoadLastLocationUseCase @Inject constructor(private val locationsRepo: LocationsRepository) {

    suspend operator fun invoke() = locationsRepo.loadLastLocation()

}