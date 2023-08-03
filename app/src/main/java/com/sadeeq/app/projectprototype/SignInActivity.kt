package com.sadeeq.app.projectprototype

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.gms.common.util.DataUtils
import com.sadeeq.app.projectprototype.base.BaseActivity
import com.sadeeq.app.projectprototype.databinding.ActivitySignInBinding
import com.sadeeq.app.projectprototype.firebase.AuthError
import com.sadeeq.app.projectprototype.firebase.AuthState
import com.sadeeq.app.projectprototype.firebase.activities.StudentsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : BaseActivity() {

    override fun onStart() {
        super.onStart()
        if (fireBaseViewModel.getCurrentUser() != null) {
            startActivity(Intent(this, StudentsActivity::class.java))
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
                }

                is AuthError.FirebaseError -> {
                    binding.progressBar.visibility = View.GONE
                    val errorMessage = authError.errorMessage
                    showTost(errorMessage)
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
}