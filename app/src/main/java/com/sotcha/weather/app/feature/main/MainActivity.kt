package com.sotcha.weather.app.feature.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.sotcha.weather.R
import com.sotcha.weather.app.feature.search.SearchActivity
import com.sotcha.weather.app.model.CityUiModel
import com.sotcha.weather.app.utils.viewBinding
import com.sotcha.weather.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(MainActivityBinding::inflate)

    // Shared view model with fragments
    private val cityViewModel: CityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        setupTabs()
        addObservers()
        addListeners()
        fetchLastCity()
    }

    private fun addListeners() {
        binding.setCity.setOnClickListener { startSearchActivity() }
    }

    private fun addObservers() {
        cityViewModel.city.observe(this) { city ->
            onCityUpdate(city)
        }
    }


    private fun fetchLastCity() {
        cityViewModel.fetchLastCity()
    }


    private fun onCityUpdate(city: CityUiModel?) {
        if (city != null) {
            //Update the ui
            binding.toolbar.title = city.name
            binding.tabLayout.visibility = View.VISIBLE
            binding.viewPager2.visibility = View.VISIBLE
            binding.noCityWrapper.visibility = View.GONE
        } else {
            binding.viewPager2.visibility = View.GONE
            binding.noCityWrapper.visibility = View.VISIBLE
            binding.tabLayout.visibility = View.GONE
            binding.toolbar.title = getString(R.string.app_name)


        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                startSearchActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupTabs() {
        val mainViewPagerFragmentAdapter = MainViewPagerFragmentAdapter(this)
        mainViewPagerFragmentAdapter.notifyDataSetChanged()

        // It is mandatory to setup first the adapter and then use TabLayoutMediator
        binding.viewPager2.adapter = mainViewPagerFragmentAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            NOW_PAGE_INDEX -> R.drawable.ic_baseline_calendar_today_24
            HOURLY_PAGE_INDEX -> R.drawable.ic_baseline_time_24
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            NOW_PAGE_INDEX -> getString(R.string.header_now)
            HOURLY_PAGE_INDEX -> getString(R.string.header_hourly)
            else -> null
        }
    }


    private fun startSearchActivity() {
        searchActivityLauncher.launch(Intent(this, SearchActivity::class.java))
    }

    /**
    //     * Use this launcher to get on callback the activity result
    //     */
    private val searchActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            fetchLastCity()
//            if (result.resultCode == Activity.RESULT_OK) {
//
////                SearchActivity.getLocationFromResult(result.data)?.let { location ->
////                    // returned location
////                    onCitySelected(CityUiModel(location.name, location.lat, location.long))
////                }
//
//            }
        }

}
