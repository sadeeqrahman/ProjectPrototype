package com.sadeeq.app.projectprototype.base

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.transition.Fade
import android.transition.TransitionManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sadeeq.app.projectprototype.viewModels.ApiVIewModel
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.sadeeq.app.projectprototype.R
import com.sadeeq.app.projectprototype.firebase.viewModel.AuthViewModel
import com.sadeeq.app.projectprototype.roomDatabase.MovieViewModel
import com.sadeeq.app.projectprototype.viewModels.PermissionsViewModel
import java.util.Locale

open class BaseActivity : AppCompatActivity() {
    private lateinit var timer: CountDownTimer
    private var isTimerRunning = false

    val viewModel: ApiVIewModel by viewModels()
    val roomViewModel: MovieViewModel by viewModels()
    val fireBaseViewModel: AuthViewModel by viewModels()
    var permissionsViewModel: PermissionsViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        permissionsViewModel = ViewModelProvider(this).get(PermissionsViewModel::class.java)

    }

    fun showTost(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    fun restartActivity(activity: Activity) {
        val intent = activity.intent
        activity.finish()
        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        activity.startActivity(intent)
        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

    }

    fun switchTheme() {
        val newTheme = AppCompatDelegate.MODE_NIGHT_NO
        val transition = Fade()
        TransitionManager.beginDelayedTransition(findViewById(android.R.id.content), transition)
        AppCompatDelegate.setDefaultNightMode(newTheme)
    }

    fun switchTheme1() {
        val newTheme = AppCompatDelegate.MODE_NIGHT_YES
        val transition = Fade()
        TransitionManager.beginDelayedTransition(findViewById(android.R.id.content), transition)
        AppCompatDelegate.setDefaultNightMode(newTheme)
    }

      fun startTimer(durationSeconds: Long,timerTextView:TextView) {
        isTimerRunning = true
        timer = object : CountDownTimer(durationSeconds * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                val formattedTime = String.format("%02d:%02d", secondsRemaining / 60, secondsRemaining % 60)
                timerTextView.text = formattedTime

                // Apply scaling animation
                val scaleAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.scale_animation)
                timerTextView.startAnimation(scaleAnimation)
            }

            override fun onFinish() {
                isTimerRunning = false
                timerTextView.text = "00:00"
                // Perform camera capture logic here
            }
        }
        timer.start()
    }

      fun animateNotificationBell(notificationBell: ImageView) {
        val rotateAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation)
        rotateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
                // Perform any action after animation is complete
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        notificationBell.startAnimation(rotateAnimation)
    }

}