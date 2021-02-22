package com.sotcha.weather.app.di

import android.content.Context
import com.sotcha.weather.data.db.AppDatabase
import com.sotcha.weather.data.db.SearchLocationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideSearchLocationDao(appDatabase: AppDatabase): SearchLocationDao {
        return appDatabase.searchLocationDao()
    }


}
