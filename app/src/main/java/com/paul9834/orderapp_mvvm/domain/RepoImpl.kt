package com.paul9834.orderapp_mvvm.domain

import androidx.lifecycle.MutableLiveData
import com.paul9834.orderapp_mvvm.data.model.CartEntity
import com.paul9834.orderapp_mvvm.data.model.ProductItem
import com.paul9834.orderapp_mvvm.vo.Resource

class RepoImpl (private val dataSource: DataSource): Repo {

    override suspend fun getFoodList(): Resource<List<ProductItem>> {
        return dataSource.getFoodByName()
    }

    override suspend fun insertCart(cartEntity: CartEntity) {
        dataSource.insertCartItemRoom(cartEntity)
    }

    override suspend fun getCarrito(): Resource<List<CartEntity>> {
        return dataSource.getUserCart()
    }

    override suspend fun deleteCarroEntity(cartEntity: CartEntity) {
        dataSource.deleteCartItem(cartEntity)
    }

    override suspend fun getTotalOrder(): Resource<Int> {
        return dataSource.getTotalOrder()
    }


}