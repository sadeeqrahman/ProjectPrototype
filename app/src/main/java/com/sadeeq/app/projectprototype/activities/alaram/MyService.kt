package com.sadeeq.app.projectprototype.activities.alaram

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class MyService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Perform your desired action, such as showing a notification
        Toast.makeText(this, "Alarm triggered!", Toast.LENGTH_SHORT).show()

        // Return START_NOT_STICKY since this service doesn't need to be restarted
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
