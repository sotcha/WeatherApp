package com.sotcha.weather.app.feature.hourly.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sotcha.weather.app.feature.nowweather.ConditionsAdapter
import com.sotcha.weather.app.model.HourItemUiModel
import com.sotcha.weather.app.model.HourRow
import com.sotcha.weather.app.utils.loadImageFromUrl
import com.sotcha.weather.databinding.ItemHourlyRowBinding

class HourItemViewHolder(
    private val binding: ItemHourlyRowBinding,
    private val notifyCallback: (Int) -> Unit
) : HourRowViewHolder(binding.root) {
    private val conditionsAdapter: ConditionsAdapter by lazy {
        val conditionsAdapter = ConditionsAdapter()
        binding.recyclerView.apply {
            adapter = conditionsAdapter
            layoutManager = LinearLayoutManager(itemView.context)
            setHasFixedSize(true)
        }
        conditionsAdapter
    }


    override fun <T : HourRow> bind(item: T) {
        if (item is HourItemUiModel) {
            binding.hourTime.text = item.time
            binding.hourDescription.text = item.description
            binding.hourTemperature.text = item.temperature

            // Update image
            if (item.image != null) {
                binding.hourImage.loadImageFromUrl(item.image)
            } else {
                binding.hourImage.visibility = View.GONE
            }

            if (item.expandedRow) {
                conditionsAdapter.conditions = item.conditions
                binding.recyclerView.visibility = View.VISIBLE
                binding.hourArrow.rotation = 180f
            } else {
                binding.recyclerView.visibility = View.GONE
                binding.hourArrow.rotation = 0f
            }


            binding.filmForClick.setOnClickListener { onItemClick(item) }
//            binding.recyclerView.setOnClickListener { onItemClick(item) }
        }
    }

    private fun onItemClick(item: HourItemUiModel) {
        item.expandedRow = !item.expandedRow
        notifyCallback.invoke(adapterPosition)
    }

    companion object {
        fun create(parent: ViewGroup, inflater: LayoutInflater, notifyCallck: (Int) -> Unit) =
            HourItemViewHolder(ItemHourlyRowBinding.inflate(inflater, parent, false), notifyCallck)
    }

}