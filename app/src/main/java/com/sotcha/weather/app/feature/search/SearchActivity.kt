package com.sotcha.weather.app.feature.search

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.sotcha.weather.R
import com.sotcha.weather.app.utils.DebugLogHelper
import com.sotcha.weather.app.utils.viewBinding
import com.sotcha.weather.databinding.SearchActivityBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    private val binding by viewBinding(SearchActivityBinding::inflate)
    private val searchViewModel: SearchViewModel by viewModels()

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SearchFragment())
                .commitNow()
        }

        setupToolbar()
        setupSearchBar()
        addObservers()
    }

    private fun addObservers() {
        searchViewModel.locationSelected.observe(this) { location ->
            // Finish with reult
            finish()
        }
    }

    private fun setupSearchBar() {
        binding.searchView.onActionViewExpanded()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                DebugLogHelper.d("onQueryTextSubmit : $query")
                if (query != null) fetchSearch(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                DebugLogHelper.d("onQueryTextChange : $newText")

                // Start search after sometime and when chars > 1
                // Cancel previous scheduling
                handler.removeCallbacksAndMessages(null)

                if (newText != null && newText.length > 1) {
                    handler.postDelayed({ fetchSearch(newText) }, 500)
                }
                return false
            }
        })
    }


    private fun fetchSearch(term: String) {
        searchViewModel.cancelSearch()
        searchViewModel.search(term)
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setDisplayShowTitleEnabled(false)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        private const val ARG_NAME_LOCATION = "location"


    }

}