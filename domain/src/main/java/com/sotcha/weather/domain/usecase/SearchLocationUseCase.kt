package com.sotcha.weather.domain.usecase

import com.sotcha.weather.domain.repository.LocationsRepository
import javax.inject.Inject

/**
 * Use case which executes search for locations
 */
class SearchLocationUseCase @Inject constructor(private val locationsRepo: LocationsRepository) {

    suspend operator fun invoke(term: String) = locationsRepo.search(term)

}