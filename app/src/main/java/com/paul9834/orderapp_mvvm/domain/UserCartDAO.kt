package com.paul9834.orderapp_mvvm.domain

import androidx.room.*
import com.paul9834.orderapp_mvvm.data.model.*

@Dao
interface UserCartDAO {

    @Query("SELECT * FROM itemEntity")
    suspend fun getProductList():List<ItemEntity>


    @Query("SELECT SUM(producto_precio) AS value FROM itemEntity")
    suspend fun getTotalPrice(): Int?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCart(cart: Cart)


    suspend fun insertCartAndItem(cart: Cart, itemEntity: ItemEntity) {
        insertProduct(itemEntity)
        cart.id = itemEntity.id
        insertCart(cart)
    }

    //falta delete//

    @Delete
    suspend fun deleteCart(cart: Cart)


    @Delete
    suspend fun deleteProduct(product: ItemEntity)

    @Transaction
    @Query("SELECT * FROM itemEntity")
    fun getDogsAndOwners(): List<CartAndItemC>






    /* @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertCartItem(cartItem: CartItem)

 */

}