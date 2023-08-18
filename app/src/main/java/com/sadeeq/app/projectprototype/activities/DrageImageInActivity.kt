package com.sadeeq.app.projectprototype.activities

import android.annotation.SuppressLint
import android.content.ClipData
import android.graphics.Canvas
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import com.sadeeq.app.projectprototype.R
import com.sadeeq.app.projectprototype.databinding.ActivityDrageImageInBinding

class DrageImageInActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityDrageImageInBinding
    private lateinit var imageView: ImageView
    private var offsetX: Float = 0.toFloat()
    private var offsetY: Float = 0.toFloat()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_drage_image_in)

        imageView = findViewById(R.id.imageView)

        imageView = findViewById(R.id.imageView)

        imageView.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    offsetX = motionEvent.rawX - view.x
                    offsetY = motionEvent.rawY - view.y
                }

                MotionEvent.ACTION_MOVE -> {
                    val newX = motionEvent.rawX - offsetX
                    val newY = motionEvent.rawY - offsetY

                    view.x = newX
                    view.y = newY
                }

                else -> return@setOnTouchListener false
            }
            true

        }
    }
}