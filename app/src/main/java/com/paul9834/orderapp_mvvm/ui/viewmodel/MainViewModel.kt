package com.paul9834.orderapp_mvvm.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.paul9834.orderapp_mvvm.data.model.Cart
import com.paul9834.orderapp_mvvm.data.model.ItemEntity
import com.paul9834.orderapp_mvvm.domain.Repo
import com.paul9834.orderapp_mvvm.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val repo:Repo) : ViewModel() {


    /*  fun getTotal(): LiveData<Resource<Int>> {
        val result = MutableLiveData<Resource<Int>>()
        viewModelScope.launch {
            val returnedrepo = repo.getTotalOrder()
            result.postValue(returnedrepo)
        }
        return result
    }*/

    val totalOrder: MutableLiveData<Int> = MutableLiveData()

    fun result() = viewModelScope.launch {
        val returnedrepo = repo.getTotalOrder()

        totalOrder.postValue(returnedrepo)

    }

/*    fun createOrder(invoice: Invoice) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repo.createOrder(invoice)
            } catch (e :Exception) {
                Log.e("Error",   "$e error")
            }
        }
    }*/



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


    fun guardarItem(cart: Cart, itemEntity: ItemEntity) {
        viewModelScope.launch {
            repo.insertCartAndItem(cart, itemEntity)
        }
    }


    fun getDataTable() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())

        try {
            emit(repo.getCartAndItemC())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
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

    fun deleteCartEntity(cart: Cart) {
        viewModelScope.launch {
            repo.deleteCartEntity(cart)
        }
    }

    fun deleteItemEntity(itemEntity: ItemEntity) {
        viewModelScope.launch {
            repo.deleteEntity(itemEntity)
        }
    }

    fun saveCart (cart: Cart) {
        viewModelScope.launch {
            repo.addCart(cart)
        }
    }




}



