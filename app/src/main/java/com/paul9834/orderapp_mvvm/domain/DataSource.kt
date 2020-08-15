package com.paul9834.orderapp_mvvm.domain

import com.paul9834.orderapp_mvvm.data.model.Cart
import com.paul9834.orderapp_mvvm.data.model.CartAndItemC
import com.paul9834.orderapp_mvvm.data.model.ItemEntity
import com.paul9834.orderapp_mvvm.data.model.ProductItem
import com.paul9834.orderapp_mvvm.vo.Resource

interface DataSource {

    suspend fun getFoodByName (): Resource<List<ProductItem>>

    suspend fun insertCartItemRoom (itemEntity: ItemEntity)

    suspend fun getUserCart(): Resource<List<ItemEntity>>

    suspend fun deleteCartItem(itemEntity: ItemEntity)

    suspend fun getTotalOrder() : Int?

    suspend fun getDogsAndOwners(): Resource<List<CartAndItemC>>

    suspend fun insertCartAndItem(cart: Cart, itemEntity: ItemEntity)

    suspend fun deleteCart(cart: Cart)

    suspend fun addCart(cart: Cart)



}