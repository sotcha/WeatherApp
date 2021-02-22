package com.sotcha.weather.app.feature.search.adapter

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sotcha.weather.app.model.SearchLocationUiModel
import com.sotcha.weather.databinding.ItemSearchRowBinding

/**
 * View holder for a search location result
 *
 * @property binding
 * @property onItemClicked
 * @property onItemDelete
 */
class SearchLocationViewHolder(
    private val binding: ItemSearchRowBinding,
    /**
     * Action that should be done when the item is clicked
     */
    private val onItemClicked: (SearchLocationUiModel) -> Unit,
    /**
     * Action that should be done when delete button clicked
     */
    private val onItemDelete: (SearchLocationUiModel) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(searchLocationUiModel: SearchLocationUiModel) {
        binding.root.setOnClickListener { onItemClicked.invoke(searchLocationUiModel) }

        binding.name.text = searchLocationUiModel.name
        // Use bold for the saved items
        val typeFaceStyle = if (searchLocationUiModel.isSaved) Typeface.BOLD else Typeface.NORMAL
        binding.name.setTypeface(null, typeFaceStyle)


        // Add the string "area/country", if any of two is missing we do not want to add the "/"
        val areaStringBuilder = StringBuilder(searchLocationUiModel.region)
        if (areaStringBuilder.isNotEmpty() && searchLocationUiModel.country.isNotEmpty()) {
            areaStringBuilder.append("/")
        }
        areaStringBuilder.append(searchLocationUiModel.country)
        binding.area.text = areaStringBuilder.toString()

        // Delete button if this is a saved location
        if (searchLocationUiModel.isSaved) {
            binding.delete.visibility = View.VISIBLE
            binding.delete.setOnClickListener { onItemDelete.invoke(searchLocationUiModel) }
        } else {
            binding.delete.visibility = View.GONE
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            layoutInflater: LayoutInflater,
            onItemClicked: (SearchLocationUiModel) -> Unit,
            onItemDelete: (SearchLocationUiModel) -> Unit
        ) = SearchLocationViewHolder(
            ItemSearchRowBinding.inflate(layoutInflater, parent, false),
            onItemClicked,
            onItemDelete
        )
    }

}
