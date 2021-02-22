package com.sotcha.weather.data.mapper

import com.sotcha.weather.data.model.response.CurrentConditionApiRepoResponse
import com.sotcha.weather.domain.model.NowWeatherDomainModel

object CurrentWeatherMapper {
    fun map(response: CurrentConditionApiRepoResponse?): NowWeatherDomainModel? {
        if (response == null) return null
        return NowWeatherDomainModel(
            conditions = ConditonMapper.map(response),
            observationTime = response.observation_time

        )
    }

}