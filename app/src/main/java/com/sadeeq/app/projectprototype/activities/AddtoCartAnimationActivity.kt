package com.sadeeq.app.projectprototype.activities

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import com.sadeeq.app.projectprototype.R
import com.sadeeq.app.projectprototype.base.BaseActivity
import org.jsoup.Connection.Base

class AddtoCartAnimationActivity : BaseActivity() {
    private lateinit var productImageView: ImageView
    private lateinit var cartImageView: ImageView
    private lateinit var scaleAnimation: Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addto_cart_animation)

        Toast.makeText(this,checkAppVersion(),Toast.LENGTH_LONG).show()

        productImageView = findViewById(R.id.productImageView)
        cartImageView = findViewById(R.id.cartImageView)
        scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_animation)
    }

    fun addToCart(view: View) {
        // Animate the product item
        productImageView.startAnimation(scaleAnimation)

        // Property animation to move product to cart
        val moveX = ObjectAnimator.ofFloat(productImageView, "translationX", 0f, cartImageView.x)
        val moveY = ObjectAnimator.ofFloat(productImageView, "translationY", 0f, cartImageView.y)
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(moveX, moveY)
        animatorSet.duration = 500
        animatorSet.start()

        // You can also add your actual "Add to Cart" logic here
        // For example, updating the cart count or adding the item to a shopping cart object.

        // Optionally, you can also update the cart icon's visibility or animate it.
        // cartImageView.startAnimation(someOtherAnimation)
    }

}