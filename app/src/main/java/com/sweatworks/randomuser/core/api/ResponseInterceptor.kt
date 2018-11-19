package com.sweatworks.randomuser.core.api

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author Ing. Wilson Garcia
 * Created on 11/19/18
 */
class ResponseInterceptor: Interceptor {

    private val tag: String = "ResponseInterceptor"

    override fun intercept(chain: Interceptor.Chain?): Response {
        Log.d(tag, "-----------------------------------------START RESPONSE--------------------------------------------------")

        val originalResponse = chain!!.proceed(chain.request())
        Log.d(tag, originalResponse.code().toString())

        Log.d(tag,"-----------------------------------------END RESPONSE--------------------------------------------------")
        return originalResponse
    }
}