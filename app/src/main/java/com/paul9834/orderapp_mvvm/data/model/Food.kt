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
    @SerializedName("createdAt")
    val createdAt: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("img_url")
    val img_url: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("price")
    val price: Int = 0,
    @SerializedName("updatedAt")
    val updatedAt: String = ""
) : Parcelable

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