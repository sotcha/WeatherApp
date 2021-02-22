package com.sotcha.weather.app.feature.hourly

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sotcha.weather.app.common.lce.LceStateViewModel
import com.sotcha.weather.app.common.lce.Loading
import com.sotcha.weather.app.common.lce.Success
import com.sotcha.weather.app.mapper.CityModelMapper
import com.sotcha.weather.app.mapper.WeatherDomainToUiMapper
import com.sotcha.weather.app.model.CityUiModel
import com.sotcha.weather.app.model.HourRow
import com.sotcha.weather.app.utils.DebugLogHelper
import com.sotcha.weather.domain.usecase.LoadWeatherForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View model for every hour of a 5 days forecast
 *
 * @property loadWeatherForecastUseCase
 */
@HiltViewModel
class HourlyViewModel @Inject constructor(
    private val loadWeatherForecastUseCase: LoadWeatherForecastUseCase
) : LceStateViewModel() {
    private var _weatherLiveData: MutableLiveData<List<HourRow>> = MutableLiveData()
    val weatherLiveData: LiveData<List<HourRow>>
        get() = _weatherLiveData

    /**
     * Load the weather with hourly, 5 days forecast
     *
     * @param cityUiModel
     */
    fun loadWeather(cityUiModel: CityUiModel) {
        _uiState.value = Loading
        DebugLogHelper.d("HourlyViewModel.loadWeather")
        viewModelScope.launch(exceptionHandler) {
            loadWeatherForecastUseCase(CityModelMapper.mapToDomain(cityUiModel), true)
                .collect { weatherForecast ->
                    DebugLogHelper.d("HourlyViewModel.gotResult")
                    val returnList = mutableListOf<HourRow>()

                    // Iterate every day to create a common list
                    weatherForecast.weather?.forEach { weatherDomainModel ->
                        returnList.addAll(WeatherDomainToUiMapper.map(weatherDomainModel))
                    }

                    _weatherLiveData.value = returnList.toList()
                    _uiState.value = Success
                }
        }
    }

}