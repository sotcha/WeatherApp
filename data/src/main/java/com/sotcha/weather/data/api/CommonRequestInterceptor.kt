package com.sotcha.weather.data.api

import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Named

/**
 * Intercept which adds common fields for the api, which are
 * - api key
 * - return format = json
 *
 * @property apiKey
 */
class CommonRequestInterceptor @Inject constructor
    (@Named("apiKey") val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val originalBody = original.body

        val builder = FormBody.Builder()
            .add("key", apiKey)
            .add("format", "json")


        if (originalBody is FormBody) {
            for (i in 0 until originalBody.size) {
                val encodedName = originalBody.encodedName(i)
                val encodedValue = originalBody.encodedValue(i)
                builder.addEncoded(encodedName, encodedValue)
            }
        }
        val body = builder.build()

        val request = original
            .newBuilder()
            .post(body)
            .build()

//        : Request = requestBuilder.build()
        return chain.proceed(request)
    }

}