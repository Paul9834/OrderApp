package com.paul9834.orderapp_mvvm.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.paul9834.orderapp_mvvm.data.model.CartEntity
import com.paul9834.orderapp_mvvm.data.model.ProductItem
import com.paul9834.orderapp_mvvm.domain.Repo
import com.paul9834.orderapp_mvvm.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val repo:Repo) : ViewModel() {

    /*  fun setFood (foodName:String) {
        foodData.value = foodName
    }*/

    /*   init {
        setFood("omelet")
    }*/

    val getTotalOrderPrice = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getTotalOrder())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }



    val fetchProductsList = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val drinkList = repo.getFoodList()
            if (drinkList is Resource.Success) {
                if (drinkList.data.isNotEmpty()) {
                    emit(drinkList)
                } else {
                    emit(Resource.Failure(Exception("No encontrado")))
                }
            }
        } catch (e: Exception) {
            Log.e("Error", "" + e)
            emit(Resource.Failure(e))
        }
    }


    fun guardarItem(cartEntity: CartEntity) {
        viewModelScope.launch {
            repo.insertCart(cartEntity)
        }
    }

    fun getTragosFavorites() = liveData(Dispatchers.IO) {

        emit(Resource.Loading())
        try {
            emit(repo.getCarrito())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }

    }

    fun deleteCartEntity(cartEntity: CartEntity) {
        viewModelScope.launch {
            repo.deleteCarroEntity(cartEntity)
        }
    }


}



