package com.sotcha.weather.app.feature.nowweather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sotcha.weather.R
import com.sotcha.weather.app.common.base.BaseFragment
import com.sotcha.weather.app.feature.main.CityViewModel
import com.sotcha.weather.app.model.ConditionUiModel
import com.sotcha.weather.app.model.NowWeatherUiModel
import com.sotcha.weather.app.utils.DebugLogHelper
import com.sotcha.weather.app.utils.MetricFormatter
import com.sotcha.weather.app.utils.loadImageFromUrl
import com.sotcha.weather.app.utils.viewBinding
import com.sotcha.weather.databinding.NowFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NowFragment : BaseFragment() {
    private val binding by viewBinding(NowFragmentBinding::bind)
    private val nowViewModel: NowViewModel by viewModels()

    // Shared viewmodel between activity and fragment
    private val cityViewModel: CityViewModel by activityViewModels()

    private lateinit var conditionsAdapter: ConditionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return NowFragmentBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeUiState(nowViewModel)
        addObservers()
        addListeners()

        fetchWeather()
    }

    private fun addListeners() {
        binding.swipeRefresh.setOnRefreshListener {
            fetchWeather()
        }
    }

    private fun fetchWeather() {
        val city = cityViewModel.city.value
        if (city != null) {
            nowViewModel.loadWeather(city)
        } else {
            showError("No city selected")
        }
    }

    private fun addObservers() {
        nowViewModel.weatherLiveData.observe(viewLifecycleOwner) { result ->
            displayWeatherData(result)
        }

        cityViewModel.city.observe(requireActivity()) { city ->
            DebugLogHelper.d("City changed ${city?.name}")
            city?.let { fetchWeather() }
        }
    }

    private fun displayWeatherData(result: NowWeatherUiModel) {
        displayNowWeatherSection(result)
        displayNowConditions(result.conditions)
    }

    private fun displayNowConditions(conditions: List<ConditionUiModel>) {
        if (!this::conditionsAdapter.isInitialized) {
            conditionsAdapter = ConditionsAdapter()

            binding.currentConditionsRecyclerview.apply {
                adapter = conditionsAdapter
                layoutManager = LinearLayoutManager(requireActivity())
            }
        }

        conditionsAdapter.conditions = conditions
    }

    private fun displayNowWeatherSection(conditions: NowWeatherUiModel) {
        val nowBasicInfoBinding = binding.nowBasicInfo
        nowBasicInfoBinding.weatherDescription.text = conditions.weatherDescription
        nowBasicInfoBinding.nowTemperature.text =
            MetricFormatter.formatTemperature(conditions.temperature)
        nowBasicInfoBinding.feelsTemperature.text =
            getString(
                R.string.real_feel,
                MetricFormatter.formatTemperature(
                    (conditions.feelsLikeTemperature)
                )
            )

        if (conditions.weatherIconUri != null) {
            nowBasicInfoBinding.weatherImage.visibility = View.VISIBLE
            nowBasicInfoBinding.weatherImage.loadImageFromUrl(conditions.weatherIconUri)
        } else {
            nowBasicInfoBinding.weatherImage.visibility = View.GONE
        }
    }

    override fun getContentView(): View = binding.contentView

    override fun getErrorView(): View = binding.errorViewInclude.errorView

    override fun getLoadingView(): View = binding.swipeRefresh

}