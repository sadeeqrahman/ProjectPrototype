package com.sadeeq.app.projectprototype.utils.customClasses

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView

class SignatureView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private lateinit var signatureImageView: ImageView

    private val path = Path()
    private val paint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 5f
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
    }
    fun setSignatureImageView(imageView: ImageView) {
        signatureImageView = imageView
    }
    fun captureSignatureAndSetImageView() {
        val signatureBitmap: Bitmap = getSignatureBitmap()
        signatureImageView.setImageBitmap(signatureBitmap)
    }

    fun getSignatureBitmap(): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        draw(canvas)
        return bitmap
    }
    fun clearSignatureAndImageView() {
        clearSignature()
        signatureImageView.setImageBitmap(null)
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> path.moveTo(x, y)
            MotionEvent.ACTION_MOVE -> path.lineTo(x, y)
            MotionEvent.ACTION_UP -> {
                // Save the signature or perform other actions
            }
        }

        invalidate()
        return true
    }

    fun clearSignature() {
        path.reset()
        invalidate()
    }
}
