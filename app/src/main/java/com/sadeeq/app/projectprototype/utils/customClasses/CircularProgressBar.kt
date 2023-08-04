package com.sadeeq.app.projectprototype.utils.customClasses


import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.content.ContextCompat
import com.sadeeq.app.projectprototype.R

class CircularProgressBar(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val strokeWidth = 8f
    private val progressColor = ContextCompat.getColor(context, R.color.colorAccent)
    private val bgColor = ContextCompat.getColor(context, R.color.colorPrimaryDark)
    private val rectF = RectF()

    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = bgColor
        style = Paint.Style.STROKE
        strokeWidth = this@CircularProgressBar.strokeWidth
    }

    private val progressPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = progressColor
        style = Paint.Style.STROKE
        strokeWidth = this@CircularProgressBar.strokeWidth
    }

    private var progress = 0f
    private var max = 100f

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircularProgressBar)
        progress = typedArray.getFloat(R.styleable.CircularProgressBar_progress, 0f)
        max = typedArray.getFloat(R.styleable.CircularProgressBar_max, 100f)
        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = (width - strokeWidth) / 2f

        rectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius)

        canvas.drawCircle(centerX, centerY, radius, backgroundPaint)

        val sweepAngle = 360 * (progress / max)
        canvas.drawArc(rectF, -90f, sweepAngle, false, progressPaint)
    }

    fun setProgress(value: Float) {
        val animator = ValueAnimator.ofFloat(progress, value)
        animator.duration = 1000 // Change the duration as needed
        animator.interpolator = LinearInterpolator()
        animator.addUpdateListener { animation ->
            progress = animation.animatedValue as Float
            invalidate()
        }
        animator.start()
    }
}
