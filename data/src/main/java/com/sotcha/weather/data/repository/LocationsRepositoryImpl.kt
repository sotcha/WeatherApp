package com.sotcha.weather.data.repository

import android.util.Log
import com.sotcha.weather.data.api.WeatherRepoApiService
import com.sotcha.weather.data.db.SearchLocationDao
import com.sotcha.weather.data.mapper.SearchLocationMapper
import com.sotcha.weather.data.utils.SharedPrefsHelper
import com.sotcha.weather.domain.model.SearchLocationDomainModel
import com.sotcha.weather.domain.repository.LocationsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocationsRepositoryImpl
@Inject constructor(
    private val weatherRepoApiService: WeatherRepoApiService,
    private val searchLocationDao: SearchLocationDao,
    private val sharedPreferencesHelper: SharedPrefsHelper
) : LocationsRepository {

    override suspend fun search(term: String): Flow<List<SearchLocationDomainModel>> {
        var list: List<SearchLocationDomainModel>? = null

        withContext(Dispatchers.IO) {
            val searchApiResponse = weatherRepoApiService.search(term)
            // Create an empty list if an error occurs
            // The api returns error when a term is not found
            list = if (searchApiResponse.hasError()) {
                emptyList()
            } else {
                searchApiResponse.searchApi?.result
                    ?.map { SearchLocationMapper.map(it) } ?: emptyList()
            }
        }
        return flow { emit(list!!) }
    }

    override suspend fun loadSavedLocations(): Flow<List<SearchLocationDomainModel>> {
        var list: List<SearchLocationDomainModel>? = null

        withContext(Dispatchers.IO) {
            val searchLocations = searchLocationDao.getAll()
            list = searchLocations.map { SearchLocationMapper.mapFromEntity(it) }
        }
        if (list == null) list = emptyList()

        var res = ""
        list?.forEach { res = res.plus(it).plus("\n") }
        Log.d(this::class.simpleName, "list : $res")
        return flow { emit(list!!) }
    }

    override suspend fun saveLocation(
        location: SearchLocationDomainModel,
        saveOnlyId: Boolean
    ): Flow<Boolean> {
        withContext(Dispatchers.IO) {
            val rowId = if (!saveOnlyId) {
                searchLocationDao.insert(SearchLocationMapper.mapFromDomain(location))
            } else {
                location.id
            }

            sharedPreferencesHelper.put(KEY_LAST_LOCATION_ID, rowId)
        }
        return flow { emit(true) }
    }

    override suspend fun deleteLocation(location: SearchLocationDomainModel): Flow<Boolean> {
        withContext(Dispatchers.IO) {
            val row = searchLocationDao.delete(SearchLocationMapper.mapFromDomain(location))
            Log.d(this::class.simpleName, "deleted rows : $row")
        }
        return flow { emit(true) }
    }


    override suspend fun loadLastLocation(): Flow<SearchLocationDomainModel?> {
        val key: Long = sharedPreferencesHelper[KEY_LAST_LOCATION_ID, -1L]
        val loadSavedLocations = loadSavedLocations()
        val list = loadSavedLocations.first()
        return flow { emit(list.firstOrNull { it.id == key }) }
    }

    companion object {
        /**
         * Key for the last location id
         */
        private const val KEY_LAST_LOCATION_ID = "KEY_LAST_LOCATION_ID"
    }

}


