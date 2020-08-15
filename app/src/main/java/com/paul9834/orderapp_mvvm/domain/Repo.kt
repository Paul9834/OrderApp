package com.paul9834.orderapp_mvvm.domain

import com.paul9834.orderapp_mvvm.data.model.Cart
import com.paul9834.orderapp_mvvm.data.model.CartAndItemC
import com.paul9834.orderapp_mvvm.data.model.ItemEntity
import com.paul9834.orderapp_mvvm.data.model.ProductItem
import com.paul9834.orderapp_mvvm.vo.Resource

interface Repo {

    suspend fun getFoodList(): Resource<List<ProductItem>>


    suspend fun insertCart(itemEntity: ItemEntity)

    suspend fun getCarrito(): Resource<List<ItemEntity>>

    suspend fun getTotalOrder() : Int?


    suspend fun getCartAndItemC(): Resource<List<CartAndItemC>>

    suspend fun insertCartAndItem(cart: Cart, itemEntity: ItemEntity)


    suspend fun deleteCartEntity(cart: Cart)

    suspend fun deleteEntity(itemEntity: ItemEntity)

    suspend fun addCart(cart: Cart)









}