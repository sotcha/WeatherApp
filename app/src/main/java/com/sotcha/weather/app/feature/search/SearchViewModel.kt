package com.sotcha.weather.app.feature.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sotcha.weather.app.common.lce.LceStateViewModel
import com.sotcha.weather.app.common.lce.Loading
import com.sotcha.weather.app.common.lce.Success
import com.sotcha.weather.app.mapper.SearchLocationsMapper
import com.sotcha.weather.app.model.SearchLocationUiModel
import com.sotcha.weather.app.utils.DebugLogHelper
import com.sotcha.weather.domain.usecase.DeleteLocationUseCase
import com.sotcha.weather.domain.usecase.LoadLocationsUseCase
import com.sotcha.weather.domain.usecase.SaveLocationUseCase
import com.sotcha.weather.domain.usecase.SearchLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View model for search location
 *
 * @property searchLocationUseCase
 */

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchLocationUseCase: SearchLocationUseCase,
    private val saveLocationUseCase: SaveLocationUseCase,
    private val deleteLocationUseCase: DeleteLocationUseCase,
    private val loadLocationsUseCase: LoadLocationsUseCase,
) : LceStateViewModel() {
    private var searchLocationsJob: Job? = null
    private var _locations = MutableLiveData<List<SearchLocationUiModel>>()
    val locations: LiveData<List<SearchLocationUiModel>> = _locations

    private var _savedLocations = MutableLiveData<List<SearchLocationUiModel>>()
    val savedLocations: LiveData<List<SearchLocationUiModel>> = _savedLocations

    private var _locationSelected = MutableLiveData<SearchLocationUiModel>()
    val locationSelected: LiveData<SearchLocationUiModel> = _locationSelected


    /**
     * Execute location search for the [term] parameter
     *
     * @param term
     */
    fun search(term: String) {
        _uiState.value = Loading
        DebugLogHelper.d("search $term")
        searchLocationsJob = viewModelScope.launch(exceptionHandler) {
            searchLocationUseCase(term)
                .collect { results ->
                    DebugLogHelper.d("Got results : ${results.size}")
                    if (isActive) {
                        _locations.value = results.map { SearchLocationsMapper.mapToUi(it) }
                        _uiState.value = Success
                    }
                }
        }
    }

    /**
     * Ask to cancel searc job, if there is any
     *
     */
    fun cancelSearch() {
        searchLocationsJob?.cancel()
    }

    /**
     * When a location is selected from the list
     *
     * @param location
     */
    fun locationSelected(location: SearchLocationUiModel) {

        // Add the location
//        if (!location.isSaved) {
//
//            }
//        } else {
//            _locationSelected.value = location
//        }

        viewModelScope.launch(exceptionHandler) {
            saveLocationUseCase(SearchLocationsMapper.mapToDomain(location), location.isSaved)
                .collect { _ ->
                    _locationSelected.value = location
                }
        }
    }

    fun loadSavedLocations() {
        viewModelScope.launch(exceptionHandler) {
            loadLocationsUseCase()
                .collect { results ->
                    DebugLogHelper.d("Got results : ${results.size}")
                    val mapped =
                        results.map {
                            SearchLocationsMapper.mapToUi(it)
                                // Also apply saved flag since it is saved
                                .apply {
                                    isSaved = true
                                }
                        }

                    DebugLogHelper.d("mapped results  : ${results.size}")
                    _savedLocations.value = mapped
                }
        }
    }


    fun locationDeleted(location: SearchLocationUiModel) {
        viewModelScope.launch(exceptionHandler) {
            deleteLocationUseCase(SearchLocationsMapper.mapToDomain(location))
                .collect {
                    loadSavedLocations()
                }
        }
    }

}