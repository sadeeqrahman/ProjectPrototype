package com.sadeeq.app.projectprototype.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.sadeeq.app.projectprototype.R
import com.sadeeq.app.projectprototype.activities.alaram.AlarmActivity
import com.sadeeq.app.projectprototype.activities.nfc.NFCActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //startActivity(Intent(this,ImageSliderActivity::class.java))

        if (Intent.ACTION_SEND == intent.action) {
            val type = intent.type

            if (type != null) {
                when {
                    type.startsWith("image/") -> {
                        val imageUri = intent.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)
                        if (imageUri != null) {
                            // Handle the shared image here
                            displayImage(imageUri)
                        }
                    }
                    type.startsWith("video/") -> {
                        val videoUri = intent.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)
                        if (videoUri != null) {
                            // Handle the shared video here
                            playVideo(videoUri)
                        }
                    }
                    type.startsWith("application/pdf") -> {
                        val pdfUri = intent.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)
                        if (pdfUri != null) {
                            // Handle the shared PDF document here
                            openPdfDocument(pdfUri)
                        }
                    }
                    // Handle other document types as needed
                    else -> {
                        // Handle other types of shared documents here
                    }
                }
            }
        }
    }

    private fun displayImage(imageUri: Uri) {
        // Your code to display the shared image
    }

    private fun playVideo(videoUri: Uri) {
        // Your code to play the shared video
    }

    private fun openPdfDocument(pdfUri: Uri) {
        // Your code to open and display the shared PDF document

    }
}