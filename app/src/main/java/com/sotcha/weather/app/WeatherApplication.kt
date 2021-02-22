package com.sotcha.weather.app

import androidx.multidex.MultiDexApplication
import com.sotcha.weather.BuildConfig
import com.sotcha.weather.app.utils.DebugLogHelper
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class WeatherApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        DebugLogHelper.enable = BuildConfig.DEBUG
    }

}