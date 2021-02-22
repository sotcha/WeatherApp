package com.sotcha.weather.app.mapper

import com.sotcha.weather.app.model.ConditionUiModel
import com.sotcha.weather.app.utils.MetricFormatter
import com.sotcha.weather.domain.model.ConditionType
import com.sotcha.weather.domain.model.ConditionsDomailModel

object ConditionDomainToUiMapper {
    fun map(condition: ConditionsDomailModel): List<ConditionUiModel> =
        listOf(
            ConditionUiModel(
                ConditionType.WINDSPEED,
                MetricFormatter.formatSpeed(condition.windSpeed)
            ),
            ConditionUiModel(ConditionType.HUMIDITY, "${condition.humidity}%"),
            ConditionUiModel(
                ConditionType.VISIBILTY,
                MetricFormatter.formatDistance(condition.visibility)
            ),
            ConditionUiModel(
                ConditionType.PRESSURE,
                MetricFormatter.formatPressure(condition.pressure)
            )
        )
}