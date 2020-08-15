package com.paul9834.orderapp_mvvm.domain

import com.paul9834.orderapp_mvvm.data.model.Cart
import com.paul9834.orderapp_mvvm.data.model.CartAndItemC
import com.paul9834.orderapp_mvvm.data.model.ItemEntity
import com.paul9834.orderapp_mvvm.data.model.ProductItem
import com.paul9834.orderapp_mvvm.vo.Resource

class RepoImpl (private val dataSource: DataSource): Repo {

    override suspend fun getFoodList(): Resource<List<ProductItem>> {
        return dataSource.getFoodByName()
    }

    override suspend fun insertCart(itemEntity: ItemEntity) {
        dataSource.insertCartItemRoom(itemEntity)
    }

    override suspend fun getCarrito(): Resource<List<ItemEntity>> {
        return dataSource.getUserCart()
    }

    override suspend fun deleteEntity(itemEntity: ItemEntity) {
        dataSource.deleteCartItem(itemEntity)
    }

    override suspend fun addCart(cart: Cart) {
        dataSource.addCart(cart)
    }

    override suspend fun getTotalOrder(): Int? {
        return dataSource.getTotalOrder()
    }

    override suspend fun getCartAndItemC(): Resource<List<CartAndItemC>> {
        return dataSource.getDogsAndOwners()

    }

    override suspend fun insertCartAndItem(cart: Cart, itemEntity: ItemEntity) {
        return dataSource.insertCartAndItem(cart,itemEntity)
    }

    override suspend fun deleteCartEntity(cart: Cart) {
        return dataSource.deleteCart(cart)
    }


}