package com.sotcha.weather.app.feature.nowweather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sotcha.weather.R
import com.sotcha.weather.app.model.ConditionUiModel
import com.sotcha.weather.databinding.ItemConditionRowBinding
import com.sotcha.weather.domain.model.ConditionType

/**
 * Adapter for the conditions list
 *
 */
class ConditionsAdapter : RecyclerView.Adapter<ConditionsAdapter.ConditionsViewHolder>() {
    var conditions = listOf<ConditionUiModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConditionsViewHolder =
        ConditionsViewHolder.create(parent, LayoutInflater.from(parent.context))

    override fun onBindViewHolder(holder: ConditionsViewHolder, position: Int) {
        holder.bind(conditions[position])
    }

    override fun getItemCount(): Int = conditions.size


    /**
     * View holder which renders the  rows if the conditions
     *
     * @property binding
     */
    class ConditionsViewHolder(private val binding: ItemConditionRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(conditionUiModel: ConditionUiModel) {
            binding.label.text = getLabel(conditionUiModel.conditionType)
            binding.value.text = conditionUiModel.value
        }

        private fun getLabel(conditionType: ConditionType) =
            when (conditionType) {
                ConditionType.PRESSURE -> itemView.context.getString(R.string.pressure)
                ConditionType.WINDSPEED -> itemView.context.getString(R.string.windspeed)
                ConditionType.HUMIDITY -> itemView.context.getString(R.string.humidity)
                ConditionType.VISIBILTY -> itemView.context.getString(R.string.visibility)
            }

        companion object {
            fun create(parent: ViewGroup, layoutInflater: LayoutInflater) =
                ConditionsViewHolder(ItemConditionRowBinding.inflate(layoutInflater, parent, false))

        }
    }
}