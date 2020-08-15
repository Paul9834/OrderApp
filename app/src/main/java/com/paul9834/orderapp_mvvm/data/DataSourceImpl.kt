package com.paul9834.orderapp_mvvm.data


import com.paul9834.orderapp_mvvm.data.model.Cart
import com.paul9834.orderapp_mvvm.data.model.CartAndItemC
import com.paul9834.orderapp_mvvm.data.model.ItemEntity
import com.paul9834.orderapp_mvvm.data.model.ProductItem
import com.paul9834.orderapp_mvvm.domain.DataSource
import com.paul9834.orderapp_mvvm.vo.AppDataBase
import com.paul9834.orderapp_mvvm.vo.Resource
import com.paul9834.orderapp_mvvm.vo.RetrofitClient

class DataSourceImpl (private val appDataBase:AppDataBase):DataSource{

    override suspend fun getFoodByName(): Resource<List<ProductItem>> {
        return Resource.Success(RetrofitClient.webservice.getComidaByName())
    }

    override suspend fun insertCartItemRoom(itemEntity: ItemEntity) {
        appDataBase.userCart().insertProduct(itemEntity)
    }

    override suspend fun getUserCart(): Resource<List<ItemEntity>> {
        return Resource.Success(appDataBase.userCart().getProductList())
    }

    override suspend fun deleteCartItem(itemEntity: ItemEntity) {
        appDataBase.userCart().deleteProduct(itemEntity)
    }

    override suspend fun getTotalOrder(): Int? {
        return appDataBase.userCart().getTotalPrice()
    }

    override suspend fun getDogsAndOwners(): Resource<List<CartAndItemC>> {
        return Resource.Success(appDataBase.userCart().getDogsAndOwners())
    }

    override suspend fun insertCartAndItem(cart: Cart, itemEntity: ItemEntity) {
        return appDataBase.userCart().insertCartAndItem(cart, itemEntity)
    }

    override suspend fun deleteCart(cart: Cart) {
        return appDataBase.userCart().deleteCart(cart)
    }

    override suspend fun addCart(cart: Cart) {
        appDataBase.userCart().insertCart(cart)
    }



}