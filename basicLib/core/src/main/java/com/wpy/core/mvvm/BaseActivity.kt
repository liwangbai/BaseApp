package com.wpy.core.mvvm

import android.graphics.Color
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.alibaba.android.arouter.launcher.ARouter

/**
 * Created by Li Wangbai.
 * Date: 2023-07-14 01:27:00
 * Desc: 基类，拓展用
 */
abstract class BaseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFramework()
        initView(savedInstanceState)
        initData()
        registerReceiver()
        ARouter.getInstance().inject(this)
    }

    protected open fun initFramework(){
        setContentView(getLayoutId())
    }

    protected open fun initView(savedInstanceState: Bundle?){
        window.statusBarColor = Color.parseColor("#F5F5F5")
        window.navigationBarColor = Color.parseColor("#F5F5F5")
    }

    protected open fun initData() {

    }

    protected open fun registerReceiver(){

    }

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    // onBackPressed()方法过时了，推荐使用onBackPressedDispatcher实现返回动作监听
    protected open fun interceptBack(
        owner: LifecycleOwner = this,
        handler: () -> Boolean
    ) {
        onBackPressedDispatcher.addCallback(
            owner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val consumed = handler()
                    if (!consumed) {
                        isEnabled = false
                        onBackPressedDispatcher.onBackPressed()
                    }
                }
            }
        )
    }
}