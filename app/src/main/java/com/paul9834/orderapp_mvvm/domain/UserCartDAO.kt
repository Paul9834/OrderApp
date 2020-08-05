package com.paul9834.orderapp_mvvm.domain

import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.paul9834.orderapp_mvvm.data.model.CartEntity
import com.paul9834.orderapp_mvvm.data.model.ProductItem

@Dao
interface UserCartDAO {

    @Query("SELECT * FROM CartEntity")
    suspend fun getProductList():List<CartEntity>


    @Query("SELECT SUM(producto_precio) AS value FROM CartEntity")
    suspend fun getTotalPrice(): Int


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: CartEntity)

    @Delete
    suspend fun deleteProduct(product: CartEntity)


}