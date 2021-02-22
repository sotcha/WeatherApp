package com.sotcha.weather.app.feature.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sotcha.weather.app.feature.search.SearchViewModel
import com.sotcha.weather.app.model.SearchLocationUiModel

/**
 * Adapter for search location
 *
 * @property searchViewModel
 */
class SearchLocationAdapter(private val searchViewModel: SearchViewModel) :
    RecyclerView.Adapter<SearchLocationViewHolder>() {
    var items = listOf<SearchLocationUiModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchLocationViewHolder =
        SearchLocationViewHolder.create(
            parent,
            LayoutInflater.from(parent.context),
            ::onLocationSelected,
            ::onSavedLocationDelete
        )

    override fun onBindViewHolder(holder: SearchLocationViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    private fun onLocationSelected(location: SearchLocationUiModel) {
        searchViewModel.locationSelected(location)
    }

    private fun onSavedLocationDelete(location: SearchLocationUiModel) {
        searchViewModel.locationDeleted(location)
    }

}