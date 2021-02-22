package com.sotcha.weather.data.mapper

import com.sotcha.weather.data.model.entity.SearchLocationEntity
import com.sotcha.weather.data.model.response.SearchLocationRepoResponse
import com.sotcha.weather.data.model.response.ValueRepoResponse
import com.sotcha.weather.domain.model.SearchLocationDomainModel

object SearchLocationMapper {
    fun map(response: SearchLocationRepoResponse): SearchLocationDomainModel =
        SearchLocationDomainModel(
            name = getValueFromValueRepoList(response.areaName),
            region = getValueFromValueRepoList(response.region),
            country = getValueFromValueRepoList(response.country),
            lat = response.latitude.toFloatOrNull() ?: 0f,
            long = response.longitude.toFloatOrNull() ?: 0f
        )

    fun mapFromEntity(searchLocationEntity: SearchLocationEntity): SearchLocationDomainModel =
        SearchLocationDomainModel(
            id = searchLocationEntity.uid,
            name = searchLocationEntity.name,
            region = searchLocationEntity.region,
            country = searchLocationEntity.country,
            lat = searchLocationEntity.lat,
            long = searchLocationEntity.lng
        )


    private fun getValueFromValueRepoList(list: List<ValueRepoResponse>) =
        list.firstOrNull()?.value ?: ""

    fun mapFromDomain(location: SearchLocationDomainModel): SearchLocationEntity =
        SearchLocationEntity(
            uid = location.id,
            name = location.name,
            region = location.region,
            country = location.country,
            lat = location.lat,
            lng = location.long
        )
}