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

        val image: ImageView = findViewById(R.id.imageview)
        if (Intent.ACTION_SEND == intent.action && intent.type?.startsWith("image/") == true) {
            val imageUri = intent.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)
            if (imageUri != null) {
                Glide.with(this)
                    .load(imageUri)
                    .into(image)
            }
        }
    }
}