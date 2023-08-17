package com.sadeeq.app.projectprototype.utils.permissions

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object PermissionManager {
    private val cameraPermissionLiveData = MutableLiveData<Boolean>()

    private const val PERMISSION_REQUEST_CAMERA = 101

    fun requestCameraPermission(activity: Activity): LiveData<Boolean> {
        if (ContextCompat.checkSelfPermission(
                activity,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            cameraPermissionLiveData.value = true
        } else {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(android.Manifest.permission.CAMERA),
                PERMISSION_REQUEST_CAMERA
            )
        }
        return cameraPermissionLiveData
    }

    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_CAMERA) {
            cameraPermissionLiveData.value = grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
        }
    }
}
