package com.wpy.baseapp.splash.page

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.wpy.baseapp.R
import com.wpy.arouter.ARouterPath
import com.wpy.baseapp.databinding.ActivitySplashBinding
import com.wpy.baseapp.splash.viewmodel.SplashViewModel
import com.wpy.core.mvvm.BaseBVMActivity


@SuppressLint("CustomSplashScreen")
@Route(path = ARouterPath.ACTIVITY_SPLASH)
class SplashActivity : BaseBVMActivity<ActivitySplashBinding, SplashViewModel>() {
    override fun createViewModel() = SplashViewModel()

    override fun getLayoutId() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)

        Handler(Looper.getMainLooper()).postDelayed({
            ARouter.getInstance().build(ARouterPath.ACTIVITY_MAIN).navigation()
            finish()
        }, 1000)
    }
}