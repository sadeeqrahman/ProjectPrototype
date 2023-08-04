package com.sadeeq.app.projectprototype.utils.customClasses

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.sadeeq.app.projectprototype.R

class CustomLoadingButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr) {

    private val progressPaint = Paint()
    private var isLoading = false
    private var currentProgress = 0f

    init {
        // Initialize progress paint properties
        progressPaint.color = context.getColor(R.color.colorAccent)
        progressPaint.style = Paint.Style.FILL
        progressPaint.isAntiAlias = true
    }

    fun showLoading(isLoading: Boolean) {
        this.isLoading = isLoading
        if (isLoading) {
            startLoadingAnimation()
        } else {
            stopLoadingAnimation()
        }
        invalidate() // Trigger a redraw
    }

     fun startLoadingAnimation() {
        val animation = ValueAnimator.ofFloat(0f, 100f)
        animation.duration = 3000 // Animation duration in milliseconds
        animation.addUpdateListener { valueAnimator ->
            currentProgress = valueAnimator.animatedValue as Float
            invalidate() // Redraw during animation
        }
        animation.repeatCount = ValueAnimator.INFINITE
        animation.repeatMode = ValueAnimator.RESTART
        animation.start()
    }

     fun stopLoadingAnimation() {
        // Stop the animation or reset progress if needed
        currentProgress = 0f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (isLoading) {
            val centerX = width / 2f
            val centerY = height / 2f
            val radius = width / 2f

            val startAngle = -90f
            val sweepAngle = currentProgress * 3.6f // Convert progress to degrees

            // Draw the water filling effect (Arc)
            canvas.drawArc(centerX - radius, centerY - radius, centerX + radius, centerY + radius, startAngle, sweepAngle, true, progressPaint)
        }
    }
}
