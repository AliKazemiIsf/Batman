package com.test.batman.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class WebServiceGateway(
    private val config: Configuration
) {

    val retrofit: Retrofit
        get() {
            val client = OkHttpClient.Builder().apply {
                connectTimeout(180, TimeUnit.SECONDS)
                readTimeout(180, TimeUnit.SECONDS)
                writeTimeout(180, TimeUnit.SECONDS)
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(logging)
            }.build()

            return Retrofit.Builder().apply {
                baseUrl(config.hostName)
                addConverterFactory(GsonConverterFactory.create())
                client(client)
            }.build()
        }
}