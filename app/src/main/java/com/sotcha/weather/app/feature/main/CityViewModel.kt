package com.sotcha.weather.app.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sotcha.weather.app.common.lce.LceStateViewModel
import com.sotcha.weather.app.common.lce.Success
import com.sotcha.weather.app.model.CityUiModel
import com.sotcha.weather.domain.usecase.LoadLastLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CityViewModel @Inject constructor(
    private val loadLastLocationUseCase: LoadLastLocationUseCase,
) : LceStateViewModel() {
    private var _city = MutableLiveData<CityUiModel?>()
    val city: LiveData<CityUiModel?> = _city


    /**
     * Fetch last city from the database and shared preference and update [city] live data
     *
     */
    fun fetchLastCity() {
        viewModelScope.launch(exceptionHandler) {
            loadLastLocationUseCase().collect { searchLocationDomainModel ->
                _city.value = searchLocationDomainModel?.let { loc ->
                    CityUiModel(loc.name, loc.lat, loc.long)
                }
                _uiState.value = Success
            }
        }
    }

}


