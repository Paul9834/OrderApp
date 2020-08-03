package com.paul9834.orderapp_mvvm.domain

import com.paul9834.orderapp_mvvm.data.model.ProductItem
import com.paul9834.orderapp_mvvm.vo.Resource

interface Repo {

    suspend fun getFoodList(): Resource<List<ProductItem>>


}