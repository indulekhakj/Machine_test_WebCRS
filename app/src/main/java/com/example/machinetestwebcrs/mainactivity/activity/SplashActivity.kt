package com.example.machinetestwebcrs.mainactivity.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.machinetestwebcrs.R
import android.view.animation.Animation


class SplashActivity : AppCompatActivity() {

    private lateinit var iconImageView: ImageView
    private lateinit var appNameTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        iconImageView = findViewById(R.id.iconImageView)
        appNameTextView = findViewById(R.id.appNameTextView)

        Handler().postDelayed({
            val iconAnimation = AnimationUtils.loadAnimation(this, R.anim.icon_animation)
            iconAnimation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                    appNameTextView.visibility = TextView.VISIBLE
                    val textAnimation = AnimationUtils.loadAnimation(this@SplashActivity, R.anim.text_animation)
                    appNameTextView.startAnimation(textAnimation)
                }
                override fun onAnimationEnd(animation: Animation?) {

                }

                override fun onAnimationRepeat(animation: Animation?) {}
            })

            iconImageView.visibility = ImageView.VISIBLE
            iconImageView.startAnimation(iconAnimation)

        }, 1000)
        Handler().postDelayed({
            val intent = Intent(this, CategoryActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}