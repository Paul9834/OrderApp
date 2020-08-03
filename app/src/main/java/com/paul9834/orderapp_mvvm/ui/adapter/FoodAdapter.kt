package com.paul9834.orderapp_mvvm.ui.adapter

import android.content.Context
import android.util.Log
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
import kotlinx.android.synthetic.main.food_row.view.*

class FoodAdapter (private val context: Context, private val foods:MutableList<ProductItem>
,  private val itemClickListener:onButtonClickListener

): RecyclerView.Adapter<BaseViewHolder<*>> (){


    interface onButtonClickListener {

        fun onButtonClick (productItem: ProductItem, position: Int)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.food_row, parent, false))
    }

    override fun getItemCount(): Int {
        return foods.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(foods[position], position)
        }
    }

    inner class MainViewHolder(itemView: View) : BaseViewHolder<ProductItem> (itemView) {
        override fun bind(item: ProductItem, position: Int) {

            Glide.with(context)
                .load(item.img_url)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
                .into(itemView.img_trago)

            itemView.btn_add.setOnClickListener{
                Log.e("Presione", "Boton")
                itemClickListener.onButtonClick(item, position)
            }
            itemView.txt_price.text = item.price.toString()

            itemView.txt_titulo.text = item.name
            itemView.txt_description.text = item.description

        }
    }




}