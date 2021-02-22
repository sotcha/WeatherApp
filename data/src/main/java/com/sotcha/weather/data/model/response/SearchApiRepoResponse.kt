package com.sotcha.weather.data.model.response

import com.google.gson.annotations.SerializedName

data class SearchApiRepoResponse(
    @field:SerializedName("search_api") val searchApi: SearchResultRepoResponse?,
    @field:SerializedName("data") val data: ErrorApiRepoResponse?
) {
    /**
     * Return if an error exists under error api response
     */
    fun hasError(): Boolean = data?.error?.isNotEmpty() ?: false
}