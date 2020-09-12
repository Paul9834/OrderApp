package com.paul9834.orderapp_mvvm.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.transition.MaterialFade
import com.paul9834.orderapp_mvvm.R
import com.paul9834.orderapp_mvvm.data.DataSourceImpl
import com.paul9834.orderapp_mvvm.data.model.*
import com.paul9834.orderapp_mvvm.domain.RepoImpl
import com.paul9834.orderapp_mvvm.ui.adapter.CartAdapter
import com.paul9834.orderapp_mvvm.ui.viewmodel.MainViewModel
import com.paul9834.orderapp_mvvm.ui.viewmodel.VMFactory
import com.paul9834.orderapp_mvvm.vo.AppDataBase
import com.paul9834.orderapp_mvvm.vo.Resource
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_payment.*


class CartFragment : Fragment(),  CartAdapter.onButtonClickListener,CartAdapter.onMoreChooseListener, CartAdapter.onReduceChooseListener {

    private lateinit var adapterCart:CartAdapter
    private lateinit var cart_final:MutableList<CartAndItemC>

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
        btn_pay_details.hide()



        btn_pay_details.setOnClickListener{
            val total = calcOrderTotal()
            var bundle_total = bundleOf("amount" to total.toString())
            findNavController().navigate(R.id.action_cartFragment_to_paymentFragment, bundle_total)
        }
    }


    private fun setupRecyclerView() {

        rv_cart_items.layoutManager = LinearLayoutManager(requireContext())
        rv_cart_items.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

    }


    private fun setupObservers() {

        viewModel.getDataTable().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    Log.e("Result", "${result.data}")
                    val resultData = result.data
                    val cart = result.data.map {
                        CartAndItemC(it.cartEntity, it.itemEntity)
                    }.toMutableList()

                    cart_final = cart

                    adapterCart = CartAdapter(requireContext(), cart, this, this, this)
                    rv_cart_items.adapter = adapterCart


                    btn_pay_details.show()

                }
            }
        })
    }


    private fun calcOrderTotal() : Int {
        var total_order = 0;
        cart_final.forEach { item ->
            val total = item.itemEntity.price * (item.cartEntity.cantidad).toInt()
            total_order += total
        }
        return total_order;
    }


    override fun onButtonClick(cartAndItemC: CartAndItemC, position: Int) {

        viewModel.deleteCartEntity(cartAndItemC.cartEntity)
        viewModel.deleteItemEntity(cartAndItemC.itemEntity)

        adapterCart.deleteItem(position)
        Toast.makeText(requireContext(), "Se borró el articulo del carrito", Toast.LENGTH_SHORT).show()


    }

    override fun onButtonClick2(cartAndItemC: CartAndItemC, position: Int) {
/*
        val quantitiy = cartAndItemC.cart.cantidad + 1

        viewModel.saveCartEntity(cartAndItemC.cart)*/

        viewModel.saveCart(cartAndItemC.cartEntity)

        Toast.makeText(requireContext(), "Agregaste ${cartAndItemC.cartEntity.cantidad}", Toast.LENGTH_SHORT).show()
    }

    override fun onButtonClick3(cartAndItemC: CartAndItemC, position: Int) {
/*
        val quantitiy = cartAndItemC.cart.cantidad + 1

        viewModel.saveCartEntity(cartAndItemC.cart)*/

        viewModel.saveCart(cartAndItemC.cartEntity)

        Toast.makeText(requireContext(), "Eliminaste ${cartAndItemC.cartEntity.cantidad}", Toast.LENGTH_SHORT).show()
    }

}

