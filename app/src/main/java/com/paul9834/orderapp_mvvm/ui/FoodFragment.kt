package com.paul9834.orderapp_mvvm.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.paul9834.orderapp_mvvm.R
import com.paul9834.orderapp_mvvm.data.DataSourceImpl
import com.paul9834.orderapp_mvvm.domain.RepoImpl
import com.paul9834.orderapp_mvvm.ui.adapter.FoodAdapter
import com.paul9834.orderapp_mvvm.ui.viewmodel.MainViewModel
import com.paul9834.orderapp_mvvm.ui.viewmodel.VMFactory
import com.paul9834.orderapp_mvvm.vo.AppDataBase
import com.paul9834.orderapp_mvvm.vo.Resource
import kotlinx.android.synthetic.main.fragment_food.*


class FoodFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel> {
        VMFactory(
            RepoImpl(
                DataSourceImpl(
                    AppDataBase.getDatabase(requireActivity().applicationContext)
                )
            )
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipe_refresh.isEnabled = false

        setupRecyclerView()
        setupObservers ()


    }

    private fun setupObservers () {

        viewModel.fetchTragosList.observe (viewLifecycleOwner, Observer { result ->

            when (result) {
                is Resource.Loading -> {
                    Log.e("Error", "Cargando")
                    swipe_refresh.isRefreshing = true
                }
                is Resource.Success -> {
                    Log.e("Error", "Sucesss")
                    swipe_refresh.isRefreshing = false
                    foods.adapter = FoodAdapter (requireContext(), result.data.toMutableList())
                }
                is Resource.Failure -> {
                    Log.e("Error", "Fail")
                    swipe_refresh.isRefreshing = false
                    Snackbar.make(requireView(), "Ocurrio un error ${result.exception}", Snackbar.LENGTH_SHORT).show()
               }

            }


        })


    }
    fun setupRecyclerView() {

        foods.layoutManager = LinearLayoutManager(requireContext())

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food, container, false)
    }


}