package com.sotcha.weather.app.feature.hourly.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sotcha.weather.app.model.HourRow

abstract class HourRowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun <T : HourRow> bind(item: T)
}