package com.paul9834.orderapp_mvvm.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.transition.MaterialFade
import com.paul9834.orderapp_mvvm.R
import com.paul9834.orderapp_mvvm.data.DataSourceImpl
import com.paul9834.orderapp_mvvm.data.model.CartEntity
import com.paul9834.orderapp_mvvm.data.model.ProductItem
import com.paul9834.orderapp_mvvm.domain.RepoImpl
import com.paul9834.orderapp_mvvm.ui.adapter.CartAdapter
import com.paul9834.orderapp_mvvm.ui.viewmodel.MainViewModel
import com.paul9834.orderapp_mvvm.ui.viewmodel.VMFactory
import com.paul9834.orderapp_mvvm.vo.AppDataBase
import com.paul9834.orderapp_mvvm.vo.Resource
import kotlinx.android.synthetic.main.fragment_cart.*


class CartFragment : Fragment(), CartAdapter.onButtonClickListener {


    private lateinit var adapter:CartAdapter



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

        enterTransition = MaterialFade()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()

        btn_pay_details.setOnClickListener{
            findNavController().navigate(R.id.action_cartFragment_to_paymentFragment)
        }
    }


    private fun setupRecyclerView() {

        rv_cart_items.layoutManager = LinearLayoutManager(requireContext())
        rv_cart_items.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

    }


    private fun setupObservers() {

        viewModel.getTragosFavorites().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {


                    Log.e("Result", "${result.data}")


                    val cart = result.data.map {
                        ProductItem(it.id, it.createdAt, it.updatedAt, it.name, it.description, it.img_url, it.price)
                    }.toMutableList()

                    adapter = CartAdapter(requireContext(), cart, this)
                    rv_cart_items.adapter = adapter
                }

                is Resource.Failure -> {

                }
            }

        })

    }

    override fun onButtonClick(productItem: ProductItem, position: Int) {

        viewModel.deleteCartEntity(CartEntity(productItem.id, productItem.createdAt, productItem.description, productItem.img_url, productItem.name, productItem.price, productItem.updatedAt))
        adapter.deleteItem(position)
        Toast.makeText(requireContext(), "Se borró el articulo del carrito", Toast.LENGTH_SHORT).show()


    }
}

