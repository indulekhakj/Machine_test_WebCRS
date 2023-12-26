package com.example.machinetestwebcrs.mainactivity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.machinetestwebcrs.R
import com.example.machinetestwebcrs.model.DataListModel

class ProductAdapterr(private val products: List<DataListModel>) :
    RecyclerView.Adapter<ProductAdapterr.ProductViewHolder>() {
var context: ProductAdapterr =this
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productTitle: TextView = itemView.findViewById(R.id.textTitle)
        val productImage: ImageView = itemView.findViewById(R.id.img)
        // Add more views as needed
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product_adapter, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.productTitle.text = product.title

        Glide.with(holder.itemView.context) //1
            .load(product.image)
            .skipMemoryCache(true) //2
            .diskCacheStrategy(DiskCacheStrategy.NONE) //3
            .transform(CircleCrop()) //4
            .into(holder.productImage)
    }

    override fun getItemCount(): Int {
        return products.size
    }
}
