package com.sotcha.weather.app.mapper

import com.sotcha.weather.app.model.SearchLocationUiModel
import com.sotcha.weather.domain.model.SearchLocationDomainModel

/**
 * Maps [SearchLocationDomainModel] to [SearchLocationUiModel]
 */
object SearchLocationsMapper {
    fun mapToUi(domainModel: SearchLocationDomainModel): SearchLocationUiModel =
        SearchLocationUiModel(
            id = domainModel.id,
            name = domainModel.name,
            region = domainModel.region,
            country = domainModel.country,
            lat = domainModel.lat,
            long = domainModel.long,
        )

    fun mapToDomain(uiModel: SearchLocationUiModel): SearchLocationDomainModel =
        SearchLocationDomainModel(
            id = uiModel.id,
            name = uiModel.name,
            region = uiModel.region,
            country = uiModel.country,
            lat = uiModel.lat,
            long = uiModel.long,
        )


}