package com.sotcha.weather.app.feature.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sotcha.weather.app.feature.hourly.HourlyFragment
import com.sotcha.weather.app.feature.nowweather.NowFragment
import com.sotcha.weather.app.utils.DebugLogHelper

// Positions of tabs
const val NOW_PAGE_INDEX = 0
const val HOURLY_PAGE_INDEX = 1

/**
 * Adapter for the view pager
 *
 */
class MainViewPagerFragmentAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        DebugLogHelper.d("createFragment pos $position")

        return when (position) {
            NOW_PAGE_INDEX -> NowFragment()
            HOURLY_PAGE_INDEX -> HourlyFragment()

            else -> throw IndexOutOfBoundsException()
        }
    }

}