package com.sadeeq.app.projectprototype

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sadeeq.app.projectprototype.activities.AddtoCartAnimationActivity
import com.sadeeq.app.projectprototype.activities.CalenderActivity
import com.sadeeq.app.projectprototype.activities.DrageImageInActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startActivity(Intent(this, CalenderActivity::class.java))
    }
}