package com.thoughtworks.themoments.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Zhu on 2019-11-22
 */
object ApiManger {
    private val mRetrofit: Retrofit
    private val BASE_URL_FACADE_MODULE = "http://thoughtworks-ios.herokuapp.com/"
    private val DEFAULT_TIMEOUT: Long = 10

    init {
        mRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_FACADE_MODULE)
            .client(getOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        //Custom OkHttp
        val httpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)

        return httpClientBuilder.build()
    }

    fun <T> getApiService(service: Class<T>): T {
        return mRetrofit.create(service)
    }
}