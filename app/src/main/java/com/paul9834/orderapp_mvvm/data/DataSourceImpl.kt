package com.paul9834.orderapp_mvvm.data


import com.paul9834.orderapp_mvvm.data.model.ProductItem
import com.paul9834.orderapp_mvvm.domain.DataSource
import com.paul9834.orderapp_mvvm.vo.AppDataBase
import com.paul9834.orderapp_mvvm.vo.Resource
import com.paul9834.orderapp_mvvm.vo.RetrofitClient

class DataSourceImpl (private val appDataBase:AppDataBase):DataSource{
    override suspend fun getFoodByName(): Resource<List<ProductItem>> {
        return Resource.Success(RetrofitClient.webservice.getComidaByName())
    }


}