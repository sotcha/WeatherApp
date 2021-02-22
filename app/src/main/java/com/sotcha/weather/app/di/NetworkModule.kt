package com.sotcha.weather.app.di

import com.sotcha.weather.BuildConfig
import com.sotcha.weather.data.api.CommonRequestInterceptor
import com.sotcha.weather.data.api.HttpClient
import com.sotcha.weather.data.api.LoggingInterceptor
import com.sotcha.weather.data.api.WeatherRepoApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideWeatherRepoApiService(retrofit: Retrofit): WeatherRepoApiService {
        return retrofit.create(WeatherRepoApiService::class.java)
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = LoggingInterceptor.create()

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        commonRequestInterceptor: CommonRequestInterceptor
    ): OkHttpClient {
        return HttpClient.setupOkhttpClient(httpLoggingInterceptor, commonRequestInterceptor)
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        @Named("baseUrl") baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(): String = "https://api.worldweatheronline.com/premium/v1/"

    @Provides
    @Named("apiKey")
    fun provideApiKey(): String = BuildConfig.API_KEY

}
