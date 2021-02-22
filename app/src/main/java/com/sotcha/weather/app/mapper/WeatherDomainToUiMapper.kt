package com.sotcha.weather.app.mapper

import android.annotation.SuppressLint
import com.sotcha.weather.app.model.HourHeaderUiModel
import com.sotcha.weather.app.model.HourRow
import com.sotcha.weather.app.utils.MetricFormatter
import com.sotcha.weather.domain.model.WeatherDomainModel
import java.text.SimpleDateFormat
import java.util.*

object WeatherDomainToUiMapper {
    fun map(weather: WeatherDomainModel): List<HourRow> {
        val list = mutableListOf<HourRow>()
        // First add the header and then the hour items
        //
        val hourHeaderUiModel = HourHeaderUiModel(
            formatDate(weather.date),
            MetricFormatter.formatTemperature(weather.avgTemperature),
            MetricFormatter.formatTemperature(weather.minTemperature),
            MetricFormatter.formatTemperature(weather.maxTemperature)
        )
        list += hourHeaderUiModel

        weather.hourly.forEach { hourlyDomainModel ->
            list += HourlyDomainToUiMapper.map(hourlyDomainModel)
        }

        return list
    }


    @SuppressLint("SimpleDateFormat")
    private fun formatDate(date: Date?): String {
        return date?.let { date ->
            val dateFormat = SimpleDateFormat("EEE d MMM d")
            dateFormat.format(date)
        } ?: ""
    }

}

