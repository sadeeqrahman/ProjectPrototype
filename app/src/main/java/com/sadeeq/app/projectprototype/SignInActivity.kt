package com.sadeeq.app.projectprototype

import android.content.Intent
import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.sadeeq.app.projectprototype.base.BaseActivity
import com.sadeeq.app.projectprototype.databinding.ActivitySignInBinding
import com.sadeeq.app.projectprototype.firebase.AuthError
import com.sadeeq.app.projectprototype.firebase.activities.UsersActivity
import dagger.hilt.android.AndroidEntryPoint

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
            switchTheme()
//            val email = binding.emailAddress.text.toString()
//            val password = binding.password.text.toString()
//
//            if (binding.emailAddress.text.toString().isEmpty() || binding.password.text.toString()
//                    .isEmpty()
//            ) {
//                showTost("enter your credentials")
//            } else {
//                binding.progressBar.visibility = View.VISIBLE
//                fireBaseViewModel.signIn(email, password)
//            }
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

    }

    private fun switchTheme() {
        val newTheme = AppCompatDelegate.MODE_NIGHT_NO
        val transition = Fade()
        TransitionManager.beginDelayedTransition(findViewById(android.R.id.content), transition)
        AppCompatDelegate.setDefaultNightMode(newTheme)
    }


}