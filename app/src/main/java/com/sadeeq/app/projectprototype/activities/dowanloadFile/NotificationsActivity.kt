package com.sadeeq.app.projectprototype.activities.dowanloadFile

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.RemoteViews
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.sadeeq.app.projectprototype.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsActivity : AppCompatActivity() {
    private val REQUEST_CODE_ASK_PERMISSIONS = 123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)


        val downloadButton: Button = findViewById(R.id.downloadButton)
        downloadButton.setOnClickListener {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.S){
                if (isStoragePermissionGranted()) {
                    customNotification()
                }
            }else{
                customNotification()
            }


        }
        createChannel()

        Log.e("sadfsdafsadfasdf", Build.VERSION.SDK_INT.toString())


    }

    fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "my_channel_id"
            val channelName = "My Channel Name"
            val channelDescription = "My Channel Description"
            val importance =
                NotificationManager.IMPORTANCE_HIGH // You can change this importance level

            val channel = NotificationChannel(channelId, channelName, importance)
            channel.description = channelDescription

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }

    }

    fun simpleNotification() {
        val intent = Intent(this, NotificationsActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationBuilder = NotificationCompat.Builder(this, "my_channel_id")
            .setSmallIcon(R.drawable.ic_notification_bell) // Set your notification icon
            .setContentTitle("Notification Title")
            .setContentText("Notification Text")
            .setPriority(NotificationCompat.PRIORITY_HIGH) // You can change this priority
            .setContentIntent(pendingIntent)
            .setAutoCancel(true) // Removes the notification when the user taps it

        val notificationManager = NotificationManagerCompat.from(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notificationManager.notify(1, notificationBuilder.build())

    }


    fun customNotification() {
        val customLayout = RemoteViews(packageName, R.layout.custom_notification_layout)
        customLayout.setImageViewResource(R.id.custom_notification_icon, R.drawable.imagesss)

        customLayout.setTextViewText(R.id.custom_notification_title, "Custom Notification Title")
        customLayout.setTextViewText(R.id.custom_notification_text, "Custom Notification Text")
        val notificationBuilder = NotificationCompat.Builder(this, "my_channel_id")
            .setSmallIcon(R.drawable.ic_notification_bell) // Set your notification icon
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCustomBigContentView(customLayout)
            .setAutoCancel(true)

        val notificationManager = NotificationManagerCompat.from(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notificationManager.notify(1, notificationBuilder.build())


    }

    fun isStoragePermissionGranted(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED
            ) {
                // Log.v(TAG, "Permission is granted")
                true
            } else {
                // Log.v(TAG, "Permission is revoked")
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    REQUEST_CODE_ASK_PERMISSIONS
                )
                false
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            //  Log.v(TAG, "Permission is granted")
            true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_ASK_PERMISSIONS) {
            customNotification()

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_ASK_PERMISSIONS -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted, do your stuff
                customNotification()
            } else {
                // Permission Denied

            }

            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

}