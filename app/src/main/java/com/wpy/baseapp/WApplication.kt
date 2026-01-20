package com.wpy.baseapp

import android.app.Application
import android.util.Log
import com.wpy.arouter.ARouterHelper
import com.wpy.utils.AppUtils

/**
 * Created by Wangbai.
 * Date: 2023-07-16 20:52:00
 * Desc:
 */
class WApplication: Application() {
    companion object {
        private const val TAG = "WApplication"
    }
    override fun onCreate() {
        super.onCreate()

        Log.d(TAG, "application init...")
        AppUtils.init(this)
        // 初始化ARouter
        ARouterHelper.initARouter(this)
    }

}