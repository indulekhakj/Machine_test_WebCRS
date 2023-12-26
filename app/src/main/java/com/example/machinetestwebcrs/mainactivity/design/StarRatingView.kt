package com.example.machinetestwebcrs.mainactivity.design

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.graphics.PorterDuff

import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.view.View
import com.example.machinetestwebcrs.R

class StarRatingView@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val maxStars = 5
    private var rating: Float = 0f
    private val starViews = mutableListOf<ImageView>()

    init {
        orientation = HORIZONTAL
        LayoutInflater.from(context).inflate(R.layout.view_star_rating, this, true)

        for (i in 1..maxStars) {
            val starImageView = ImageView(context)
            starImageView.setImageResource(R.drawable.ic_star)

            if (i <= rating) {
                starImageView.setColorFilter(context.getColor(R.color.rel_six), PorterDuff.Mode.SRC_IN)
            } else {
                starImageView.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN)
            }

            starImageView.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f)
            starImageView.setOnClickListener { setRating(i.toFloat()) }

            starViews.add(starImageView)
            addView(starImageView)
        }
    }

    fun setRating(rating: Float) {
        this.rating = rating

        for ((index, starView) in starViews.withIndex()) {
            val isSelected = index < rating
            if (isSelected) {
                starView.setColorFilter(context.getColor(R.color.rel_six), PorterDuff.Mode.SRC_IN)
            } else {
                starView.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN)
            }
            starView.isSelected = isSelected
        }
    }

    fun getRating(): Float {
        return rating
    }
}