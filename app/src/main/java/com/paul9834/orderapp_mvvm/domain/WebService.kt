package com.paul9834.orderapp_mvvm.domain

import com.paul9834.orderapp_mvvm.data.model.Food
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET ("api/")
    suspend fun getComidaByName (@Query ("q") tragoName:String): Food


}