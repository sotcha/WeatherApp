package com.sotcha.weather.domain.usecase

import com.sotcha.weather.domain.model.SearchLocationDomainModel
import com.sotcha.weather.domain.repository.LocationsRepository
import javax.inject.Inject

/**
 * Use case which deletes a location from the storage
 */
class DeleteLocationUseCase @Inject constructor(private val locationsRepo: LocationsRepository) {

    suspend operator fun invoke(location: SearchLocationDomainModel) =
        locationsRepo.deleteLocation(location)

}