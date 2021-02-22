package com.sotcha.weather.app.mapper

import android.net.Uri
import com.sotcha.weather.app.model.HourItemUiModel
import com.sotcha.weather.app.utils.MetricFormatter
import com.sotcha.weather.domain.model.HourlyDomainModel

object HourlyDomainToUiMapper {
    fun map(hourlyDomainModel: HourlyDomainModel): HourItemUiModel =
        HourItemUiModel(
            time = String.format("%02d:00", hourlyDomainModel.time),
            temperature = MetricFormatter.formatTemperature(hourlyDomainModel.conditions.temperature),
            description = hourlyDomainModel.conditions.weatherDescription,
            image = Uri.parse(hourlyDomainModel.conditions.weatherIconUrl),
            conditions = ConditionDomainToUiMapper.map(hourlyDomainModel.conditions)
        )
}

