package com.paul9834.orderapp_mvvm.domain

import androidx.lifecycle.MutableLiveData
import com.paul9834.orderapp_mvvm.data.model.CartEntity
import com.paul9834.orderapp_mvvm.data.model.ProductItem
import com.paul9834.orderapp_mvvm.vo.Resource

interface DataSource {

    suspend fun getFoodByName (): Resource<List<ProductItem>>

    suspend fun insertCartItemRoom (cartEntity: CartEntity)

    suspend fun getUserCart(): Resource<List<CartEntity>>

    suspend fun deleteCartItem(cartEntity: CartEntity)

    suspend fun getTotalOrder() : Resource<Int>


}