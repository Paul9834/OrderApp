package com.paul9834.orderapp_mvvm.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.paul9834.orderapp_mvvm.R
import com.paul9834.orderapp_mvvm.base.BaseViewHolder
import com.paul9834.orderapp_mvvm.data.model.ProductItem
import kotlinx.android.synthetic.main.cart_row.view.*
import kotlinx.android.synthetic.main.food_row.view.*

class CartAdapter (private val context: Context, private val cart:MutableList<ProductItem>
                   ,  private val itemClickListener: CartAdapter.onButtonClickListener

): RecyclerView.Adapter<BaseViewHolder<*>> (){


    interface onButtonClickListener {
        fun onButtonClick (productItem: ProductItem, position: Int)
    }

    fun deleteItem(position: Int){
        cart.removeAt(position)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.cart_row, parent, false))
    }

    override fun getItemCount(): Int {
        return cart.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(cart[position], position)
        }

    }

    inner class MainViewHolder(itemView: View) :BaseViewHolder<ProductItem> (itemView) {
        override fun bind(item: ProductItem, position: Int) {

            Glide.with(context)
                    .load(item.img_url)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
                    .into(itemView.img_trago_cart)

            itemView.txt_price_cart.text = "$ ${item.price}"
            itemView.txt_titulo_cart.text = item.name
            itemView.txt_description_cart.text = item.description

            itemView.btn_remove_cart.setOnClickListener{
                itemClickListener.onButtonClick(item, position)

            }

        }
    }
}