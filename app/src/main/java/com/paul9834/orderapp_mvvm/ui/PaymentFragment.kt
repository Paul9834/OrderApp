package com.paul9834.orderapp_mvvm.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import com.paul9834.orderapp_mvvm.R
import com.paul9834.orderapp_mvvm.data.DataSourceImpl
import com.paul9834.orderapp_mvvm.data.model.CartAndItemC
import com.paul9834.orderapp_mvvm.data.model.Invoice
import com.paul9834.orderapp_mvvm.data.model.InvoiceItems
import com.paul9834.orderapp_mvvm.data.model.Item
import com.paul9834.orderapp_mvvm.domain.RepoImpl
import com.paul9834.orderapp_mvvm.ui.viewmodel.MainViewModel
import com.paul9834.orderapp_mvvm.ui.viewmodel.VMFactory
import com.paul9834.orderapp_mvvm.vo.AppDataBase
import com.paul9834.orderapp_mvvm.vo.Resource
import kotlinx.android.synthetic.main.fragment_payment.*


class PaymentFragment : Fragment() {

    private lateinit var invoice : Invoice
    private var invoiceItems: MutableList<InvoiceItems> = mutableListOf()
    private var item: List<CartAndItemC> = mutableListOf()


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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        setupObserverItems ()


        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onResume() {
        super.onResume()
        
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserverItems ()


        Toast.makeText(requireContext(), "${arguments?.getString("amount")}", Toast.LENGTH_SHORT).show()
        txt_total_price.text = arguments?.getString("amount")

        // calcOrderTotal(factura)

        floating_action_button_payment.setOnClickListener {
            setupForm ()
        }

    }


    private fun setupForm () {

        val name = txt_name_client.editText?.text
        val address = txt_name_client2.editText?.text
        val phone = txt_name_client3.editText?.text

        txt_name_client.error = null
        txt_name_client2.error = null
        txt_name_client3.error = null


        if (!(name.isNullOrBlank())) {
            if (!(address.isNullOrBlank())) {
                if (!(phone.isNullOrBlank())) {


                    for (i in item) {
                        val item:Item = Item(i.itemEntity.id, i.itemEntity.name, i.itemEntity.description, i.itemEntity.price, i.itemEntity.img_url, i.itemEntity.createdAt, i.itemEntity.updatedAt)
                        invoiceItems.add(InvoiceItems(i.cartEntity.cantidad.toInt(),item))
                    }

                    val factura = Invoice("name.toString()", "address.toString()", 1000, invoiceItems)

                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()

                    val gson = Gson()
                    val json = gson.toJson(factura)

                    Log.e("ResultInvoice", "$json Holaa")

                } else {
                    txt_name_client3.requestFocus()
                    txt_name_client3.error = "Verifica los datos ingresados"
                }
            } else {
                txt_name_client2.requestFocus()
                txt_name_client2.error = "Verifica los datos ingresados"
            }
        } else {
            txt_name_client.requestFocus()
            txt_name_client.error = "Verifica los datos ingresados"
        }
    }


    private fun setupObserverItems () {
        viewModel.getDataTable().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Success -> {
                    Log.e("Result", "${result.data}")
                    val resultData = result.data

                    val cart = result.data.map {
                        CartAndItemC(it.cartEntity, it.itemEntity)
                    }.toMutableList()

                    item = cart

                  }
                }
            })

    }
    private fun createOrder () {


    }
}