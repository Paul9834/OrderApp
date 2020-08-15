package com.paul9834.orderapp_mvvm.data.model
import android.os.Parcelable
import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Invoice (
        @SerializedName("nombre") val nombre : String,
        @SerializedName("direccion") val direccion : String,
        @SerializedName("telefono") val telefono : Int,
        @SerializedName("invoiceItems") val invoiceItems : List<InvoiceItems>
) : Parcelable


@Parcelize
data class InvoiceItems (
        @SerializedName("quantitiy") val quantitiy : Int,
        @SerializedName("item") val item : Item
)  : Parcelable

@Parcelize
data class Item (
        @SerializedName("id") val id : Int,
        @SerializedName("name") val name : String,
        @SerializedName("description") val description : String,
        @SerializedName("price") val price : Int,
        @SerializedName("img_url") val img_url : String,
        @SerializedName("createdAt") val createdAt : String,
        @SerializedName("updatedAt") val updatedAt : String
) : Parcelable


@Entity(foreignKeys = [ForeignKey(entity = ItemEntity::class,
        parentColumns = arrayOf("id"), childColumns = arrayOf("id"), onDelete = CASCADE)])
data class Cart (
        @PrimaryKey (autoGenerate = true)
        var id: Int = 0,
        var cantidad: Long = 1
)

data class CartAndItemC(
        @Relation(
                parentColumn = "id",
                entityColumn = "id"
        )
        val cart: Cart,
        @Embedded val itemEntity: ItemEntity
)




