package com.example.machinetestwebcrs.mainactivity.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.machinetestwebcrs.R
import com.example.machinetestwebcrs.mainactivity.activity.ListDetailActivity
import com.example.machinetestwebcrs.model.CategoryWithProducts
import com.example.machinetestwebcrs.model.DataListModel
import com.example.machinetestwebcrs.recyclerviewmanager.OnItemClickListener
import com.example.machinetestwebcrs.recyclerviewmanager.addOnItemClickListener

class CategoryAdapter(var categories: List<CategoryWithProducts>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryTitle: TextView = itemView.findViewById(R.id.categoryTitle)
        val productsRecyclerView: RecyclerView = itemView.findViewById(R.id.productsRecyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.categoryTitle.text = category.category

        val productAdapter = ProductAdapterr(category.products)
        holder.productsRecyclerView.adapter = productAdapter
        holder.productsRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.productsRecyclerView.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(pos: Int, view: View) {

                val intent = Intent(holder.itemView.context, ListDetailActivity::class.java)
                intent.putExtra("ITEM_ID",category.products[pos].id )
                holder.itemView.context.startActivity(intent)

            }

        })
    }


    override fun getItemCount(): Int {
        return categories.size
    }
}