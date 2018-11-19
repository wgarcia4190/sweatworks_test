package com.sweatworks.randomuser.core.api

import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import com.sweatworks.randomuser.util.Constants

/**
 * @author Ing. Wilson Garcia
 * Created on 11/19/18
 */
class RequestInterceptor(val context: Context): Interceptor {

    private val tag: String = "RequestInterceptor"

    override fun intercept(chain: Interceptor.Chain?): Response {
        Log.d(tag, "-----------------------------------------START REQUEST--------------------------------------------------")

        val original = chain!!.request()
        val url = original.url().newBuilder()
                .addQueryParameter(Constants.SEED_PARAMETER, Constants.CUSTOM_SEED)
                .addQueryParameter(Constants.RESULTS_PARAMETER, "50")
                .addQueryParameter(Constants.INC_PARAMETER, Constants.INC_VALUES)
                .build()

        val requestBuilder = original.newBuilder()

        requestBuilder.header(Constants.contentType, Constants.applicationJson)

        Log.d(tag, original.toString())
        Log.d(tag, "-----------------------------------------END REQUEST--------------------------------------------------")
        return chain.proceed(requestBuilder.url(url).build())
    }

}