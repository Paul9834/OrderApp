package com.paul9834.orderapp_mvvm.domain

import androidx.lifecycle.MutableLiveData
import com.paul9834.orderapp_mvvm.data.model.CartEntity
import com.paul9834.orderapp_mvvm.data.model.ProductItem
import com.paul9834.orderapp_mvvm.vo.Resource

interface Repo {

    suspend fun getFoodList(): Resource<List<ProductItem>>


    suspend fun insertCart(cartEntity: CartEntity)

    suspend fun getCarrito(): Resource<List<CartEntity>>


    suspend fun deleteCarroEntity(cartEntity: CartEntity)

    suspend fun getTotalOrder() : Resource<Int>




}