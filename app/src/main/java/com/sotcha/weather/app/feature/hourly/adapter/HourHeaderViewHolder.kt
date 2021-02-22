package com.sotcha.weather.app.feature.hourly.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sotcha.weather.R
import com.sotcha.weather.app.model.HourHeaderUiModel
import com.sotcha.weather.app.model.HourRow
import com.sotcha.weather.databinding.ItemHourlyHeaderBinding

class HourHeaderViewHolder(private val binding: ItemHourlyHeaderBinding) :
    HourRowViewHolder(binding.root) {

    //    override fun   bind(item: T) {
//
//    }
    override fun <T : HourRow> bind(item: T) {
        if (item is HourHeaderUiModel) {
            binding.dayName.text = item.dateName

            binding.dayAvgTemperature.text = item.avgTemperature
            binding.dayMaxTemp.text =
                itemView.context.getString(R.string.hourly_max_temperature, item.maxTemperature)
            binding.dayMinTemp.text =
                itemView.context.getString(R.string.hourly_min_temperature, item.minTemperature)
        }

    }


    companion object {
        fun create(parent: ViewGroup, inflater: LayoutInflater) =
            HourHeaderViewHolder(ItemHourlyHeaderBinding.inflate(inflater, parent, false))
    }
}