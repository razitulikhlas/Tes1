package com.proyekakhir.suitmedia1.utils

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.navigation.NavController

private const val NAVIGATION_SAFE_TAG = "SAFE_NAVIGATION"

@SuppressLint("UnsafeNavigation")
fun NavController.navigateSafe(@IdRes action: Int, args: Bundle? = null): Boolean {
    return try {
        navigate(action, args)
        true
    } catch (t: Throwable) {
        Log.e(NAVIGATION_SAFE_TAG, "navigation error for action $action")
        false
    }
}