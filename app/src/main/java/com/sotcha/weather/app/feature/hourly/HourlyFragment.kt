package com.sotcha.weather.app.feature.hourly

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sotcha.weather.R
import com.sotcha.weather.app.common.base.BaseFragment
import com.sotcha.weather.app.feature.main.CityViewModel
import com.sotcha.weather.app.utils.DebugLogHelper
import com.sotcha.weather.app.utils.viewBinding
import com.sotcha.weather.databinding.HourlyFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HourlyFragment : BaseFragment() {
    private val binding by viewBinding(HourlyFragmentBinding::bind)
    private val hourlyViewModel: HourlyViewModel by viewModels()

    // Shared viewmodel between activity and fragment
    private val cityViewModel: CityViewModel by activityViewModels()

    private lateinit var hourlyAdapter: HourlyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return HourlyFragmentBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeUiState(hourlyViewModel)
        initRecyclerView()
        addObservers()

        addListeners()
        fetchWeather()
        showError(getString(R.string.no_city_selected))
    }


    private fun initRecyclerView() {
        hourlyAdapter = HourlyAdapter()

        binding.recyclerView.apply {
            adapter = hourlyAdapter
            addItemDecoration(
                DividerItemDecoration(
                    ContextThemeWrapper(requireActivity(), requireActivity().theme),
                    DividerItemDecoration.VERTICAL
                )
            )
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    private fun addListeners() {
        binding.swipeRefresh.setOnRefreshListener {
            fetchWeather()
        }
    }

    private fun fetchWeather() {
        val city = cityViewModel.city.value
        if (city != null) {
            DebugLogHelper.d("Fetch weather for ${city.name}")
            hourlyViewModel.loadWeather(city)
        } else {
            showError(getString(R.string.no_city_selected))
        }
    }

    private fun addObservers() {
        hourlyViewModel.weatherLiveData.observe(viewLifecycleOwner) { result ->
            hourlyAdapter.items = result
        }

        cityViewModel.city.observe(requireActivity()) { city ->
            DebugLogHelper.d("City changed ${city?.name}")
            city?.let { fetchWeather() }
        }
    }

    override fun getContentView(): View = binding.recyclerView

    override fun getErrorView(): View = binding.errorViewInclude.errorView

    override fun getLoadingView(): View = binding.swipeRefresh

}