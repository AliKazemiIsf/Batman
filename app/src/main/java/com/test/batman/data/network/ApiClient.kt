package com.test.batman.data.network

class ApiClient(
    private val config: Configuration
){
    fun getInterface(): ApiInterface =
        WebServiceGateway(config)
        .retrofit
        .create(ApiInterface::class.java)
}