package com.paul9834.orderapp_mvvm.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.paul9834.orderapp_mvvm.R
import com.paul9834.orderapp_mvvm.data.DataSourceImpl
import com.paul9834.orderapp_mvvm.data.model.ProductItem
import com.paul9834.orderapp_mvvm.domain.RepoImpl
import com.paul9834.orderapp_mvvm.ui.viewmodel.MainViewModel
import com.paul9834.orderapp_mvvm.ui.viewmodel.VMFactory
import com.paul9834.orderapp_mvvm.vo.AppDataBase
import kotlinx.android.synthetic.main.fragment_product_details.*


class ProductDetailsFragment : Fragment() {


    private lateinit var productItem: ProductItem

    private val viewModel by activityViewModels<MainViewModel> {
        VMFactory(
            RepoImpl(
                DataSourceImpl(
                    AppDataBase.getDatabase(requireActivity().applicationContext)
                )
            )
        )
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_details, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireArguments().let { bundle ->

            productItem = bundle.getParcelable<ProductItem>("product")!!

            Log.e("Detalles:", "$productItem")

        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireContext()).load(productItem.img_url)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
            .into(img_product)


        txt_titulo_product.text = productItem.name
        txt_precio_product.text = "$ " + productItem.price
        txt_description_product.text = productItem.description


    }

}