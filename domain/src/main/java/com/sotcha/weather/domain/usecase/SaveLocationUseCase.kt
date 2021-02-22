package com.sotcha.weather.domain.usecase

import com.sotcha.weather.domain.model.SearchLocationDomainModel
import com.sotcha.weather.domain.repository.LocationsRepository
import javax.inject.Inject

/**
 * Use case which executes save location
 */
class SaveLocationUseCase @Inject constructor(
    private val locationsRepo: LocationsRepository
) {

    suspend operator fun invoke(location: SearchLocationDomainModel, saveOnlyId: Boolean) =
        locationsRepo.saveLocation(location, saveOnlyId)

}