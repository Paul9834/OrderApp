package com.paul9834.orderapp_mvvm.domain

import com.paul9834.orderapp_mvvm.data.model.Food
import com.paul9834.orderapp_mvvm.data.model.ResultsItem
import com.paul9834.orderapp_mvvm.vo.Resource

interface Repo {

    suspend fun getFoodList(nombreComida:String): Resource<List<ResultsItem>>


}