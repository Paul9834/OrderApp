package com.paul9834.orderapp_mvvm.data


import androidx.lifecycle.MutableLiveData
import com.paul9834.orderapp_mvvm.data.model.CartEntity
import com.paul9834.orderapp_mvvm.data.model.ProductItem
import com.paul9834.orderapp_mvvm.domain.DataSource
import com.paul9834.orderapp_mvvm.vo.AppDataBase
import com.paul9834.orderapp_mvvm.vo.Resource
import com.paul9834.orderapp_mvvm.vo.RetrofitClient

class DataSourceImpl (private val appDataBase:AppDataBase):DataSource{

    override suspend fun getFoodByName(): Resource<List<ProductItem>> {
        return Resource.Success(RetrofitClient.webservice.getComidaByName())
    }

    override suspend fun insertCartItemRoom(cartEntity: CartEntity) {
        appDataBase.userCart().insertProduct(cartEntity)
    }

    override suspend fun getUserCart(): Resource<List<CartEntity>> {
        return Resource.Success(appDataBase.userCart().getProductList())
    }

    override suspend fun deleteCartItem(cartEntity: CartEntity) {
        appDataBase.userCart().deleteProduct(cartEntity)
    }

    override suspend fun getTotalOrder(): Resource<Int> {
        return Resource.Success(appDataBase.userCart().getTotalPrice())
    }


}