package com.wpy.utils

import android.app.Application
import android.util.Log

object AppUtils {

    private const val TAG = "AppUtils"
    lateinit var app: Application

    fun init(application: Application) {
        app = application
        Log.d(TAG, "app init.")
    }

}