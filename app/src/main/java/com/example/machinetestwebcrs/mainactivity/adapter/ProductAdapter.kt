package com.example.machinetestwebcrs.mainactivity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

import com.example.machinetestwebcrs.R
import com.example.machinetestwebcrs.model.DataListModel
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop


class ProductAdapter : PagingDataAdapter<DataListModel, ProductAdapter.ProductViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_adapter, parent, false)
        return ProductViewHolder(view)
    }
    fun getItemAtPosition(position: Int): DataListModel? {
        return getItem(position)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        product?.let {
            holder.bind(it)
        }
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(product: DataListModel) {


            itemView.findViewById<TextView>(R.id.textTitle).text = product.title
            var productimg=itemView.findViewById<ImageView>(R.id.img)

            Glide.with(itemView) //1
                .load(product.image)
                .skipMemoryCache(true) //2
                .diskCacheStrategy(DiskCacheStrategy.NONE) //3
                .transform(CircleCrop()) //4
                .into(productimg)

        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataListModel>() {
            override fun areItemsTheSame(oldItem: DataListModel, newItem: DataListModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DataListModel, newItem: DataListModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}