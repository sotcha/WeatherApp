package com.sotcha.weather.app.mapper

import android.net.Uri
import com.sotcha.weather.app.model.NowWeatherUiModel
import com.sotcha.weather.domain.model.NowWeatherDomainModel

object NowWeatherDomainToUiMapper {
    fun map(nowWeatherDomain: NowWeatherDomainModel): NowWeatherUiModel =
        NowWeatherUiModel(
            nowWeatherDomain.conditions.temperature,
            nowWeatherDomain.conditions.feelsLikeTemperature,
            Uri.parse(nowWeatherDomain.conditions.weatherIconUrl),
            nowWeatherDomain.conditions.weatherDescription,
            ConditionDomainToUiMapper.map(nowWeatherDomain.conditions)
        )


}