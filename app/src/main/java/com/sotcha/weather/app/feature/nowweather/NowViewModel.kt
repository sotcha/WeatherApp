package com.sotcha.weather.app.feature.nowweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sotcha.weather.app.common.lce.LceStateViewModel
import com.sotcha.weather.app.common.lce.Loading
import com.sotcha.weather.app.common.lce.Success
import com.sotcha.weather.app.mapper.CityModelMapper
import com.sotcha.weather.app.mapper.NowWeatherDomainToUiMapper
import com.sotcha.weather.app.model.CityUiModel
import com.sotcha.weather.app.model.NowWeatherUiModel
import com.sotcha.weather.app.utils.DebugLogHelper
import com.sotcha.weather.domain.usecase.LoadWeatherForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NowViewModel @Inject constructor(
    private val loadWeatherForecastUseCase: LoadWeatherForecastUseCase
) : LceStateViewModel() {
    private var _weatherLiveData: MutableLiveData<NowWeatherUiModel> = MutableLiveData()
    val weatherLiveData: LiveData<NowWeatherUiModel>
        get() = _weatherLiveData

    private var loadWeatheJob: Job? = null

    /**
     * Load the weather and update [weatherLiveData] with the result
     *
     * @param cityUiModel
     */
    fun loadWeather(cityUiModel: CityUiModel) {
        _uiState.value = Loading
        DebugLogHelper.d("NowViewModel.loadWeather")
        loadWeatheJob = viewModelScope.launch(exceptionHandler) {
            loadWeatherForecastUseCase(CityModelMapper.mapToDomain(cityUiModel))
                .collect { weatherForecast ->
                    DebugLogHelper.d("NowViewModel.gotResult")
                    // if weatherForecast.current is null it will throw an exception and we will catching
                    // showing the error label
                    _weatherLiveData.value =
                        NowWeatherDomainToUiMapper.map(weatherForecast.current!!)
                    _uiState.value = Success
                }
        }
    }

    override fun onCleared() {
        loadWeatheJob?.cancel()
    }

}