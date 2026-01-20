package com.wpy.core.mvvm

import android.graphics.Color
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.wpy.core.R


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
}