package com.paul9834.orderapp_mvvm.data

import com.paul9834.orderapp_mvvm.data.model.Food
import com.paul9834.orderapp_mvvm.data.model.ResultsItem
import com.paul9834.orderapp_mvvm.domain.DataSource
import com.paul9834.orderapp_mvvm.vo.AppDataBase
import com.paul9834.orderapp_mvvm.vo.Resource
import com.paul9834.orderapp_mvvm.vo.RetrofitClient

class DataSourceImpl (private val appDataBase:AppDataBase):DataSource{

    override suspend fun getFoodByName(foodName: String): Resource<List<ResultsItem>> {
        return Resource.Success(RetrofitClient.webservice.getComidaByName(foodName).drinksList)
    }


}