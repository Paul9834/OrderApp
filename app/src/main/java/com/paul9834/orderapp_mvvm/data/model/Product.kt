package com.paul9834.orderapp_mvvm.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class Product : ArrayList<ProductItem>()


@Parcelize
data class ProductItem(

        @SerializedName("id")
        val id: Int = 0,

        @SerializedName("createdAt")
        val createdAt: String = "",

        @SerializedName("updatedAt")
        val updatedAt: String = "",

        @SerializedName("name")
        val name: String = "",

        @SerializedName("description")
        val description: String = "",

        @SerializedName("img_url")
        val img_url: String = "",
        @SerializedName("price")
        val price: Int = 0

) : Parcelable

//CORREGIR //

@Entity(tableName = "cartEntity")
data class CartEntity(
        @PrimaryKey
        val id: Int = 0,
        @ColumnInfo(name = "producto_createdat")
        val createdAt: String = "",
        @ColumnInfo(name = "producto_descripcion")
        val description: String = "",
        @ColumnInfo(name = "producto_imagen")
        val img_url: String = "",
        @ColumnInfo(name = "producto_nombre")
        val name: String = "",
        @ColumnInfo(name = "producto_precio")
        val price: Int = 0,
        @ColumnInfo(name = "producto_updatedAt")
        val updatedAt: String = ""     )



