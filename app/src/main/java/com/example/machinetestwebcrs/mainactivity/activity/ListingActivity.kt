package com.example.machinetestwebcrs.mainactivity.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.machinetestwebcrs.R
import com.example.machinetestwebcrs.mainactivity.adapter.ProductAdapter
import com.example.machinetestwebcrs.model.DataListModel
import com.example.machinetestwebcrs.model.ProductViewModel
import com.example.machinetestwebcrs.recyclerviewmanager.OnItemClickListener
import com.example.machinetestwebcrs.recyclerviewmanager.addOnItemClickListener
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import androidx.room.Room
import com.example.machinetestwebcrs.mainactivity.design.CustomProgressBar
import com.example.machinetestwebcrs.roomdb.AppDatabase
import com.example.machinetestwebcrs.roomdb.ProductEntity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class ListingActivity: AppCompatActivity() {

var context:Context=this

    private lateinit var layoutManager: LinearLayoutManager
    lateinit var datalist:List<DataListModel?>
    lateinit var recyclerView:RecyclerView
    private lateinit var productViewModel: ProductViewModel
    private val productAdapter = ProductAdapter()
    var customProgressBar: CustomProgressBar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing)
        customProgressBar = CustomProgressBar(context!!)
        customProgressBar!!.show()
        initFn()
        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java) // Initialize productViewModel
        observeProducts()
    }
    private fun initFn() {

        recyclerView=findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = productAdapter
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager
        recyclerView.adapter=productAdapter
        recyclerView.addOnItemClickListener(object : OnItemClickListener{
            override fun onItemClicked(position: Int, view: View) {
                val clickedItem = productAdapter.getItemAtPosition(position)

                clickedItem?.let {
                    val intent = Intent(context, ListDetailActivity::class.java)
                    intent.putExtra("ITEM_ID", it.id)
                    startActivity(intent)
                }


            }

        })

    }
    private fun observeProducts() {
        customProgressBar!!.hide()

        lifecycleScope.launch {
            productViewModel.getProducts().collectLatest { pagingData ->
                productAdapter.submitData(pagingData)
                val database = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java, "app_database"
                ).build()
                val productDao = database.productDao()

                val products = pagingData.map {
                        product ->
                    ProductEntity(
                        title = product.title,
                        price = product.price,
                        description = product.description,
                        category = product.category,
                        image = product.image
                    ) }
                productDao.insertAll(products)
            }
        }
    }
}