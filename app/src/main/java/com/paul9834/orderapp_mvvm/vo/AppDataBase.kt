package com.paul9834.orderapp_mvvm.vo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.paul9834.orderapp_mvvm.data.model.*
import com.paul9834.orderapp_mvvm.domain.UserCartDAO

@Database(entities = [ItemEntity::class, Cart::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun userCart(): UserCartDAO

    companion object {
        private var INSTACE: AppDataBase? = null
        fun getDatabase(context: Context):AppDataBase {
            INSTACE = INSTACE ?: Room.inMemoryDatabaseBuilder(context.applicationContext, AppDataBase::class.java).build()
            return INSTACE!!
        }
        fun destroyInstance () {
            INSTACE = null
        }
    }

}