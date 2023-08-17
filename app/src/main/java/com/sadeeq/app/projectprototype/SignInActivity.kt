package com.sadeeq.app.projectprototype

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.sadeeq.app.projectprototype.base.BaseActivity
import com.sadeeq.app.projectprototype.databinding.ActivitySignInBinding
import com.sadeeq.app.projectprototype.firebase.AuthError
import com.sadeeq.app.projectprototype.firebase.activities.UsersActivity
import com.sadeeq.app.projectprototype.utils.LanguageUtils
import com.sadeeq.app.projectprototype.utils.permissions.PermissionManager
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class SignInActivity : BaseActivity() {

    override fun onStart() {
        super.onStart()
        if (fireBaseViewModel.getCurrentUser() != null) {
            startActivity(Intent(this, UsersActivity::class.java))
        }
    }

    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)

        binding.signIn.setOnClickListener {
            val email = binding.emailAddress.text.toString()
            val password = binding.password.text.toString()

            if (binding.emailAddress.text.toString().isEmpty() || binding.password.text.toString()
                    .isEmpty()
            ) {
                showTost("enter your credentials")
            } else {
                binding.progressBar.visibility = View.VISIBLE
                fireBaseViewModel.signIn(email, password)
            }
        }

        fireBaseViewModel.authError.observe(this) { authError ->
            when (authError) {
                is AuthError.NoInternet -> {
                    binding.progressBar.visibility = View.GONE
                    showTost("")
                }

                is AuthError.FirebaseError -> {
                    binding.progressBar.visibility = View.GONE
                    val errorMessage = authError.errorMessage
                    showTost(errorMessage)
                }

                else -> {
                    showTost("")
                }
            }
        }

        binding.signUp.setOnClickListener {
            val email = binding.emailAddress.text.toString()
            val password = binding.password.text.toString()
            if (binding.emailAddress.text.toString().isEmpty() || binding.password.text.toString()
                    .isEmpty()
            ) {
                showTost("enter your credentials")
            } else {
                binding.progressBar.visibility = View.VISIBLE
                fireBaseViewModel.signUp(email, password)
            }

        }

        binding.english.setOnClickListener {
            LanguageUtils.changeLanguage(this, "en")
            restartActivity(this)

        }
        binding.urdu.setOnClickListener {
            LanguageUtils.changeLanguage(this, "ur")
            restartActivity(this)
        }

        permissionsViewModel?.requestCameraPermission()?.observe(this) { granted ->
            if (granted) {
                Toast.makeText(this, "Camera permission granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_LONG).show()
            }
        }

        startTimer(10, binding.timerTextView)

        animateNotificationBell(binding.notificationBell)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}