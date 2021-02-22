package com.sotcha.weather.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


object HttpClient {
    fun setupOkhttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        commonRequestInterceptor: CommonRequestInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(commonRequestInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .build()
    }
}



