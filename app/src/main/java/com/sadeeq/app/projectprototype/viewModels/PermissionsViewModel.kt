package com.sadeeq.app.projectprototype.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sadeeq.app.projectprototype.base.BaseActivity
import com.sadeeq.app.projectprototype.utils.permissions.PermissionManager

class PermissionsViewModel(val baseActivity: BaseActivity) : ViewModel() {
    fun requestCameraPermission(): LiveData<Boolean> {
        return PermissionManager.requestCameraPermission(baseActivity)
    }
}