package com.paul9834.orderapp_mvvm.domain

import androidx.room.*
import com.paul9834.orderapp_mvvm.data.model.DrinkEntity

@Dao
interface TragosDAO {

    @Query("SELECT * FROM tragosEntity")
    suspend fun getFavoriteAllDrinks():List<DrinkEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(trago:DrinkEntity)

    @Delete
    suspend fun deleteDrink(drink: DrinkEntity)


}