package com.sotcha.weather.app.mapper

import com.sotcha.weather.app.model.CityUiModel
import com.sotcha.weather.domain.model.CityDomainModel

object CityModelMapper {
    fun mapToUi(city: CityDomainModel): CityUiModel =
        CityUiModel(
            name = city.name,
            lat = city.lat,
            long = city.long
        )

    fun mapToDomain(city: CityUiModel): CityDomainModel =
        CityDomainModel(
            name = city.name,
            lat = city.lat,
            long = city.long
        )
}