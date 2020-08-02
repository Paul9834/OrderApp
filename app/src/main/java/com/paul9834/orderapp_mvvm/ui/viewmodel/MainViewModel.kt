package com.paul9834.orderapp_mvvm.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.paul9834.orderapp_mvvm.domain.Repo
import com.paul9834.orderapp_mvvm.vo.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainViewModel(private val repo:Repo) : ViewModel() {

    private val foodData = MutableLiveData<String> ()

    fun setFood (foodName:String) {
        foodData.value = foodName
    }

    init {
        setFood("omelet")
    }

    val fetchTragosList = foodData.distinctUntilChanged().switchMap { nombreTrago ->
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                val drinkList = repo.getFoodList(nombreTrago)
                if (drinkList is Resource.Success) {
                    if (drinkList.data != null) {
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
    }




}