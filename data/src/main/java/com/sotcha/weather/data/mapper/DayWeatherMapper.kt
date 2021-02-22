package com.sotcha.weather.data.mapper

import android.annotation.SuppressLint
import com.sotcha.weather.data.model.response.WeatherDayApiRepoResponse
import com.sotcha.weather.domain.model.WeatherDomainModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DayWeatherMapper {
    fun map(response: WeatherDayApiRepoResponse): WeatherDomainModel =
        WeatherDomainModel(
            date = parseDate(response.date) ,
            avgTemperature = response.avgtempC.toIntOrNull() ?: 0,
            maxTemperature = response.maxtempC.toIntOrNull() ?: 0,
            minTemperature = response.mintempC.toIntOrNull() ?: 0,
            hourly = response.hourly.map { HourlyMapper.map(it) }


        )


    @SuppressLint("SimpleDateFormat")
    private fun parseDate(dateStr: String): Date? {
        val format = SimpleDateFormat("yyyy-MM-dd")
        try {
            return format.parse(dateStr)
        } catch (e: ParseException) {

        }
        return null
    }

}