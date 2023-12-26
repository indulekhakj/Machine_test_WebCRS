package com.example.machinetestwebcrs.mainactivity.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.machinetestwebcrs.R
import com.example.machinetestwebcrs.api.ApiService
import com.example.machinetestwebcrs.mainactivity.adapter.CategoryAdapter
import com.example.machinetestwebcrs.mainactivity.design.CustomProgressBar
import com.example.machinetestwebcrs.model.DataProcessor
import com.example.machinetestwebcrs.model.ProductViewModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoryActivity : AppCompatActivity() {

    var context: Context =this
    private lateinit var apiService: ApiService
    private lateinit var dataProcessor: DataProcessor
    private lateinit var layoutManager: LinearLayoutManager

    lateinit var recyclerView: RecyclerView
    private lateinit var productViewModel: ProductViewModel
    private var categoryAdapter = CategoryAdapter(emptyList())
    var customProgressBar: CustomProgressBar? = null
    var currentPage=1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing)
        customProgressBar = CustomProgressBar(context!!)
        customProgressBar!!.show()
        initFn()
        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java) // Initialize productViewModel
        observeProducts(currentPage)
        setupPagination()
    }
    private fun initFn() {
        dataProcessor = DataProcessor()
        apiService = createApiService()
        recyclerView = findViewById(R.id.recyclerView)
        layoutManager = LinearLayoutManager(this)
         categoryAdapter = CategoryAdapter(emptyList())
        recyclerView.adapter = categoryAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }
    private fun observeProducts(page: Int) {
        customProgressBar!!.hide()

        lifecycleScope.launch {
            try {
                val groupedProducts = dataProcessor.getProductsGroupedByCategory(apiService, page, 20)
                categoryAdapter.categories = groupedProducts
                categoryAdapter.notifyDataSetChanged()
            } catch (e: Exception) {
            }
        }
    }
    private fun setupPagination() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                    currentPage++
                    observeProducts(currentPage)
                }
            }
        })
    }

    private fun createApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/") // Replace with your base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }
}