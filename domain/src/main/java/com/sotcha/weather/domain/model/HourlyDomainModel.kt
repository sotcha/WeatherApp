package com.sotcha.weather.domain.model

/**
 * Hourly forecast model for a specific day
 *
 */
data class HourlyDomainModel(
    /**
     * Time of the day ie 0,1 ..17, 18
     */
    val time: Int,
    val conditions: ConditionsDomailModel
)