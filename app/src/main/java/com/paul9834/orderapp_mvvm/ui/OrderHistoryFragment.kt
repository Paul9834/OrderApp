package com.paul9834.orderapp_mvvm.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.paul9834.orderapp_mvvm.R
import com.paul9834.orderapp_mvvm.data.DataSourceImpl
import com.paul9834.orderapp_mvvm.data.model.Product
import com.paul9834.orderapp_mvvm.data.model.ProductItem
import com.paul9834.orderapp_mvvm.domain.RepoImpl
import com.paul9834.orderapp_mvvm.ui.viewmodel.MainViewModel
import com.paul9834.orderapp_mvvm.ui.viewmodel.VMFactory
import com.paul9834.orderapp_mvvm.vo.AppDataBase


class OrderHistoryFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_history, container, false)
    }



}