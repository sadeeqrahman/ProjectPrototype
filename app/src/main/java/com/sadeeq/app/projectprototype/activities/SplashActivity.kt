package com.sadeeq.app.projectprototype.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sadeeq.app.projectprototype.R
import com.sadeeq.app.projectprototype.activities.alaram.AlarmActivity
import com.sadeeq.app.projectprototype.activities.nfc.NFCActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startActivity(Intent(this,ImageSliderActivity::class.java))
    }
}