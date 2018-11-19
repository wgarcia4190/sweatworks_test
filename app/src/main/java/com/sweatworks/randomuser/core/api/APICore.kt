package com.sweatworks.randomuser.core.api

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Ing. Wilson Garcia
 * Created on 11/19/18
 */
open class APICore(context: Context?, baseUrl: String) {
    protected var retrofit: Retrofit? = null
        private set

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient(context!!))
                .addConverterFactory(ConverterFactory())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }

    private fun getOkHttpClient(context: Context): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .followRedirects(false)
                .connectTimeout(90, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS)
                .addInterceptor(RequestInterceptor(context))
                .addInterceptor(ResponseInterceptor())
                .addInterceptor(interceptor).build()
    }

}

