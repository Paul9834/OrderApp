package com.paul9834.orderapp_mvvm.vo

import com.google.gson.GsonBuilder
import com.paul9834.orderapp_mvvm.domain.WebService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val webservice: WebService by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.0.5:5000/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)

    }

}