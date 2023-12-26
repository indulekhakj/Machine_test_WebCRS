package com.example.machinetestwebcrs.mainactivity.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.machinetestwebcrs.R
import com.example.machinetestwebcrs.api.RetrofitClient
import com.example.machinetestwebcrs.mainactivity.design.CustomProgressBar
import com.example.machinetestwebcrs.mainactivity.design.StarRatingView
import com.example.machinetestwebcrs.model.DataDetailListModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListDetailActivity:AppCompatActivity() {
    var context: Context =this
    var customProgressBar: CustomProgressBar? = null
    lateinit var product_title:TextView
    lateinit var product_image:ImageView
    lateinit var product_description:TextView
    lateinit var product_price:TextView
    lateinit var ratingView: StarRatingView
    lateinit var rate_count:TextView
    lateinit var back_arrow:ConstraintLayout
    lateinit var back_btn:Button
    var productId=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listdetail)
        customProgressBar = CustomProgressBar(context!!)
        customProgressBar!!.show()
        initFn()



    }

    private fun initFn() {
        product_title=findViewById(R.id.product_title)
        product_image=findViewById(R.id.productimg)
        product_description=findViewById(R.id.description)
        product_price=findViewById(R.id.price)
        ratingView = findViewById(R.id.starRatingView)
        rate_count=findViewById(R.id.rate_count)
        back_arrow=findViewById(R.id.goback_con)
        back_btn=findViewById(R.id.backbtn)
        back_btn.setOnClickListener {
            finish()
        }
        back_arrow.setOnClickListener {
            finish()
        }

        productId = intent.getIntExtra("ITEM_ID",0)
        CoroutineScope(Dispatchers.Main).launch {
            val productDetail = withContext(Dispatchers.IO) {
                RetrofitClient.fetchProductDetail(productId)
            }
            updateUI(productDetail)
        }
    }
    private fun updateUI(productDetail: DataDetailListModel) {
        Log.e("title",productDetail.title)
product_title.text=productDetail.title
        Glide.with(context) //1
            .load(productDetail.image)
            .skipMemoryCache(true) //2
            .diskCacheStrategy(DiskCacheStrategy.NONE) //3
            .transform(CircleCrop()) //4
            .into(product_image)
        product_description.text=productDetail.description
        var amount=productDetail.price
        val amountString = "\u20B9 $amount"
        product_price.text=amountString
        var rating=productDetail.rating.rate.toFloat()
        ratingView.setRating(rating)
        rate_count.text=productDetail.rating.count.toString()+" ratings"
customProgressBar!!.hide()

    }

}
