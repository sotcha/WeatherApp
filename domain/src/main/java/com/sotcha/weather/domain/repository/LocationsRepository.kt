package com.sotcha.weather.domain.repository

import com.sotcha.weather.domain.model.SearchLocationDomainModel
import kotlinx.coroutines.flow.Flow

/**
 * Repository which :
 * - executes search functionality and returns a list of locations
 * - Load, save, delete locations from storage
 *
 */
interface LocationsRepository {

    suspend fun search(term: String): Flow<List<SearchLocationDomainModel>>

    suspend fun loadSavedLocations(): Flow<List<SearchLocationDomainModel>>

    suspend fun saveLocation(location: SearchLocationDomainModel, saveOnlyId:Boolean): Flow<Boolean>

    suspend fun deleteLocation(location: SearchLocationDomainModel): Flow<Boolean>

    suspend fun loadLastLocation(): Flow<SearchLocationDomainModel?>
}