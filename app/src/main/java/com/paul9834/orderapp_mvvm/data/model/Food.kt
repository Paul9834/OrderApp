package com.paul9834.orderapp_mvvm.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Food(
    @field:SerializedName("href")
    val href: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("version")
    val version: Double? = null,

    @field:SerializedName("results")
    val drinksList:List<ResultsItem> = listOf()
)

data class ResultsItem(

    @field:SerializedName("thumbnail")
    val thumbnail: String? = null,

    @field:SerializedName("ingredients")
    val ingredients: String? = null,

    @field:SerializedName("href")
    val href: String? = null,

    @field:SerializedName("title")
    val title: String? = null

)

//CORREGIR //

@Entity(tableName = "tragosEntity")
data class DrinkEntity(
    @PrimaryKey
    val tragoId: String,
    @ColumnInfo(name = "trago_imagen")
    val imagen: String = "",
    @ColumnInfo(name = "trago_nombre")
    val nombre: String = "",
    @ColumnInfo(name = "trago_descripcion")
    val descripcion: String = "",
    @ColumnInfo(name = "trago_has_alcohol")
    val hasAlcohol:String = "Non_Alcoholic"
)