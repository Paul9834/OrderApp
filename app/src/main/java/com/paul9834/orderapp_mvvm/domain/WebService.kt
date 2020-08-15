package com.paul9834.orderapp_mvvm.domain

import com.paul9834.orderapp_mvvm.data.model.Invoice
import com.paul9834.orderapp_mvvm.data.model.Product
import com.paul9834.orderapp_mvvm.data.model.ProductItem
import retrofit2.http.GET
import retrofit2.http.POST

interface WebService {

    @GET ("v1/api/stock")
    suspend fun getComidaByName (): Product


    @POST ("v1/api/order")
    suspend fun createOrder(invoice: Invoice): Invoice


}