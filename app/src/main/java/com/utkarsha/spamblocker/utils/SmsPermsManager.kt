package com.utkarsha.spamblocker.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object SmsPermsManager {
    private const val SMS_PERMISSION_REQUEST_CODE = 123

    // Check if the "read SMS" permission is granted
    fun isReadSmsPermissionGranted(activity: Activity): Boolean {
        val permissionStatus = ContextCompat.checkSelfPermission(
            activity,
            Manifest.permission.READ_SMS
        )
        return permissionStatus == PackageManager.PERMISSION_GRANTED
    }

    // Request the "read SMS" permission
    fun requestReadSmsPermission(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.READ_SMS),
            SMS_PERMISSION_REQUEST_CODE
        )
    }
}