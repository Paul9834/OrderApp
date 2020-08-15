package com.paul9834.orderapp_mvvm.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.paul9834.orderapp_mvvm.R
import com.paul9834.orderapp_mvvm.base.BaseViewHolder
import com.paul9834.orderapp_mvvm.data.model.CartAndItemC
import com.paul9834.orderapp_mvvm.data.model.ProductItem
import kotlinx.android.synthetic.main.cart_row.view.*
import kotlinx.android.synthetic.main.food_row.view.*

class CartAdapter (private val context: Context, private val cart:MutableList<CartAndItemC>
                   ,  private val itemClickListener:onButtonClickListener,
                   private val itemClickListener2:onMoreChooseListener,
                   private val itemClickListener3:onReduceChooseListener

                   ): RecyclerView.Adapter<BaseViewHolder<*>> (){


    interface onButtonClickListener {
        fun onButtonClick (cartAndItemC: CartAndItemC, position: Int)
    }


    interface onMoreChooseListener {
        fun onButtonClick2(cartAndItemC: CartAndItemC, position: Int)
    }

    interface onReduceChooseListener {
        fun onButtonClick3(cartAndItemC: CartAndItemC, position: Int)
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

    inner class MainViewHolder(itemView: View) :BaseViewHolder<CartAndItemC> (itemView) {
        override fun bind(item: CartAndItemC, position: Int) {

            Glide.with(context)
                    .load(item.itemEntity.img_url)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
                    .into(itemView.img_trago_cart)

            itemView.txt_price_cart.text = "$ ${item.itemEntity.price}"
            itemView.txt_titulo_cart.text = item.itemEntity.name
            itemView.txt_description_cart.text = item.itemEntity.description

            itemView.btn_remove_cart.setOnClickListener{
                itemClickListener.onButtonClick(item, position)
            }

            itemView.txt_items_count.text = (item.cart.cantidad).toString()

            itemView.arrowBtn2.setOnClickListener{
                itemView.txt_aditional.text = "Agregas un producto adicional"
                val itemMoreProduct = item
                itemMoreProduct.cart.cantidad += 1
                itemView.txt_items_count.text = (itemMoreProduct.cart.cantidad).toString()
                itemClickListener2.onButtonClick2(itemMoreProduct, position)
            }

            itemView.arrowBtn.setOnClickListener{
                val itemMoreProduct = item

                if (itemMoreProduct.cart.cantidad > 1) {
                    itemView.txt_aditional.text = "Eliminaste un producto." +
                            ""
                    itemMoreProduct.cart.cantidad -= 1
                    itemView.txt_items_count.text = (itemMoreProduct.cart.cantidad).toString()
                    itemClickListener3.onButtonClick3(itemMoreProduct, position)
                }

            }



        }
    }
}