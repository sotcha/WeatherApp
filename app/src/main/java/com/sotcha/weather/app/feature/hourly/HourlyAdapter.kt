package com.sotcha.weather.app.feature.hourly

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sotcha.weather.app.feature.hourly.adapter.HourHeaderViewHolder
import com.sotcha.weather.app.feature.hourly.adapter.HourItemViewHolder
import com.sotcha.weather.app.feature.hourly.adapter.HourRowViewHolder
import com.sotcha.weather.app.model.HourHeaderUiModel
import com.sotcha.weather.app.model.HourItemUiModel
import com.sotcha.weather.app.model.HourRow
import com.sotcha.weather.app.utils.DebugLogHelper

/**
 * [RecyclerView] adapter for the list for hourly.
 *
 * The adapter contains :
 * - Header
 * - Hour item
 */
class HourlyAdapter : RecyclerView.Adapter<HourRowViewHolder>() {
    var items = listOf<HourRow>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HourRowViewHolder {
        DebugLogHelper.d("onCreateViewHolder : $viewType")
        return when (viewType) {
            VIEW_TYPE_HEADER ->
                HourHeaderViewHolder.create(
                    parent,
                    LayoutInflater.from(parent.context)
                )
            else -> HourItemViewHolder.create(
                parent, LayoutInflater.from(parent.context)
            ) { pos ->
                notifyItemChanged(pos)
            }
        }
    }

    override fun onBindViewHolder(holder: HourRowViewHolder, position: Int) {
        DebugLogHelper.d("onBindViewHolder : $position")
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    // Get from the items the class and then the correct view type based on the map
    override fun getItemViewType(position: Int): Int = VIEW_TYPES_MAP[items[position]::class]!!


    companion object {
        // Available types for this adapter
        private const val VIEW_TYPE_HEADER = 1
        private const val VIEW_TYPE_ITEM = 2

        /**
         * Map [HourRow] subclasses to view types
         */
        private val VIEW_TYPES_MAP = mapOf(
            HourHeaderUiModel::class to VIEW_TYPE_HEADER,
            HourItemUiModel::class to VIEW_TYPE_ITEM
        )
    }

}
