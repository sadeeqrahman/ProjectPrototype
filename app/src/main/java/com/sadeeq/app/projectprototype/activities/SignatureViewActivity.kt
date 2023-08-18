package com.sadeeq.app.projectprototype.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.sadeeq.app.projectprototype.R
import com.sadeeq.app.projectprototype.databinding.ActivitySignatureViewBinding

class SignatureViewActivity : AppCompatActivity() {
    private lateinit var _binding: ActivitySignatureViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_signature_view)
        _binding.clearSignature.setOnClickListener {
            _binding.signatureImage.setImageBitmap(_binding.signatureView.getSignatureBitmap())
            _binding.signatureView.clearSignature()

        }

    }
}