package com.sotcha.weather.data.mapper

import com.sotcha.weather.data.model.response.HourlyApiRepoResponse
import com.sotcha.weather.domain.model.HourlyDomainModel

object HourlyMapper {
    fun map(response: HourlyApiRepoResponse): HourlyDomainModel {
        val time = response.time.toIntOrNull() ?: 0
        return HourlyDomainModel(
            time = time / 100,
            conditions = ConditonMapper.map(response)
        )
    }

}