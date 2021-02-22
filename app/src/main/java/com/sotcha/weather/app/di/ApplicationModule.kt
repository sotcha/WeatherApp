package com.sotcha.weather.app.di

import android.content.Context
import android.content.SharedPreferences
import com.sotcha.weather.data.utils.SharedPrefsHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {

    @Provides
    fun provideSharePreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(SharedPrefsHelper.PREF_NAME, Context.MODE_PRIVATE)

    @Provides
    fun provideSharedPrefsHelper(sharedPreferences: SharedPreferences): SharedPrefsHelper =
        SharedPrefsHelper(sharedPreferences)

}
