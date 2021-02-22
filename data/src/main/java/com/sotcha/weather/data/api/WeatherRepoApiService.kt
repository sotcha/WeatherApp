package com.sotcha.weather.data.api

import com.sotcha.weather.data.model.response.SearchApiRepoResponse
import com.sotcha.weather.data.model.response.WeatherApiRepoResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface WeatherRepoApiService {
    @FormUrlEncoded
    @POST("search.ashx")
    suspend fun search(@Field("q") location: String): SearchApiRepoResponse

    @FormUrlEncoded
    @POST("weather.ashx")
    suspend fun weather(
        @Field("q") location: String,
        @Field("num_of_days") numberOfDays: String? = null,
        @Field("tp") hourInterval: String? = null
    ): WeatherApiRepoResponse

}