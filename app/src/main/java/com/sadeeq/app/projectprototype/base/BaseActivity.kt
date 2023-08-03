package com.sadeeq.app.projectprototype.base

import androidx.appcompat.app.AppCompatActivity
import com.sadeeq.app.projectprototype.viewModels.ApiVIewModel
import androidx.activity.viewModels

open class BaseActivity : AppCompatActivity() {
    val viewModel: ApiVIewModel by viewModels()
}