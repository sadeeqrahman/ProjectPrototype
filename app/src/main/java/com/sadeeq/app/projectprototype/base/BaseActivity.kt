package com.sadeeq.app.projectprototype.base

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.transition.Fade
import android.transition.TransitionManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sadeeq.app.projectprototype.viewModels.ApiVIewModel
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.sadeeq.app.projectprototype.R
import com.sadeeq.app.projectprototype.firebase.viewModel.AuthViewModel
import com.sadeeq.app.projectprototype.roomDatabase.MovieViewModel
import java.util.Locale

open class BaseActivity : AppCompatActivity() {
    val viewModel: ApiVIewModel by viewModels()
    val roomViewModel: MovieViewModel by viewModels()
    val fireBaseViewModel: AuthViewModel by viewModels()


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







}