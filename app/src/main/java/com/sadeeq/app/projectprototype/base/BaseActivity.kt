package com.sadeeq.app.projectprototype.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sadeeq.app.projectprototype.viewModels.ApiVIewModel
import androidx.activity.viewModels
import com.sadeeq.app.projectprototype.firebase.viewModel.AuthViewModel
import com.sadeeq.app.projectprototype.roomDatabase.MovieViewModel

open class BaseActivity : AppCompatActivity() {
    val viewModel: ApiVIewModel by viewModels()
    val roomViewModel: MovieViewModel by viewModels()
    val fireBaseViewModel: AuthViewModel by viewModels()


    fun showTost(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}