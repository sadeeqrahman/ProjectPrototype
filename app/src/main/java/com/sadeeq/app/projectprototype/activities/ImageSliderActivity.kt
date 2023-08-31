package com.sadeeq.app.projectprototype.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.sadeeq.app.projectprototype.R

class ImageSliderActivity : AppCompatActivity() {
    private val images = listOf(
        R.drawable.imagesss,
        R.drawable.imagesss,
        R.drawable.imagesss
    )

    private lateinit var imageSliderAdapter: ImageSliderAdapter
    private val handler = Handler(Looper.getMainLooper())
    private var currentPage = 0
    private lateinit var viewPager:ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_slider)
        viewPager = findViewById(R.id.viewPager)
        imageSliderAdapter = ImageSliderAdapter(images)
        viewPager.adapter = imageSliderAdapter
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        // Auto-scroll the ViewPager2
        handler.postDelayed(scrollRunnable, AUTO_SCROLL_DELAY)
        val indicatorLayout = findViewById<LinearLayout>(R.id.indicatorLayout)
        setupIndicators(images.size, indicatorLayout)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(scrollRunnable)
    }

    private val scrollRunnable = object : Runnable {
        override fun run() {
            currentPage = (currentPage + 1) % images.size
            viewPager.setCurrentItem(currentPage, true)
            handler.postDelayed(this, AUTO_SCROLL_DELAY)
        }
    }

    companion object {
        private const val AUTO_SCROLL_DELAY = 3000L // 3 seconds
    }

    private fun setupIndicators(count: Int, indicatorLayout: LinearLayout) {
        val indicators = arrayOfNulls<ImageView>(count)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)

        for (i in 0 until count) {
            indicators[i] = ImageView(this)
            indicators[i]?.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_check_circle_outline_24))
            indicators[i]?.layoutParams = layoutParams
            indicatorLayout.addView(indicators[i])
        }

        indicators[0]?.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_circle_24))
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                for (i in 0 until count) {
                    indicators[i]?.setImageDrawable(ContextCompat.getDrawable(this@ImageSliderActivity, R.drawable.baseline_check_circle_outline_24))
                }
                indicators[position]?.setImageDrawable(ContextCompat.getDrawable(this@ImageSliderActivity, R.drawable.baseline_circle_24))
            }
        })
    }
}