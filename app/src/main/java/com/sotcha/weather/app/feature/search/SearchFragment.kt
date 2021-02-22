package com.sotcha.weather.app.feature.search

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sotcha.weather.app.common.base.BaseFragment
import com.sotcha.weather.app.feature.search.adapter.SearchLocationAdapter
import com.sotcha.weather.app.model.SearchLocationUiModel
import com.sotcha.weather.app.utils.viewBinding
import com.sotcha.weather.databinding.SearchFragmentBinding

class SearchFragment : BaseFragment() {
    private val binding by viewBinding(SearchFragmentBinding::bind)
    private val searchViewModel: SearchViewModel by activityViewModels()
    private val searchLocationAdapter by lazy {
        SearchLocationAdapter(searchViewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return SearchFragmentBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUiState(searchViewModel)
        setupRecyclerView()

        showContent()
        observerSearchResults()
        loadSavedLocations()
    }

    private fun loadSavedLocations() {
        searchViewModel.loadSavedLocations()
    }


    private fun observerSearchResults() {
        searchViewModel.locations.observe(requireActivity()) {
            updateResultList()
        }

        searchViewModel.savedLocations.observe(requireActivity()) {
            updateResultList()
        }
    }

    private fun updateResultList() {
        val locations = mutableListOf<SearchLocationUiModel>()

        if (!searchViewModel.savedLocations.value.isNullOrEmpty()) {
            locations.addAll(searchViewModel.savedLocations.value!!)
        }
        if (!searchViewModel.locations.value.isNullOrEmpty()) {
            locations.addAll(searchViewModel.locations.value!!)
        }

        binding.emptyView.visibility = if (locations.isEmpty()) View.VISIBLE else View.GONE
        searchLocationAdapter.items = locations
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            adapter = searchLocationAdapter
            layoutManager = LinearLayoutManager(requireActivity())
            addItemDecoration(
                DividerItemDecoration(
                    ContextThemeWrapper(requireActivity(), requireActivity().theme),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun getContentView(): View = binding.recyclerView

    override fun getErrorView(): View = binding.errorViewInclude.errorView

    override fun getLoadingView(): View = binding.loadingView
}