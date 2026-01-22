package com.wpy.baseapp

import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.alibaba.android.arouter.facade.annotation.Route
import com.wpy.ui.TabBtnClickCallback
import com.wpy.arouter.ARouterPath
import com.wpy.baseapp.databinding.ActivityMainBinding
import com.wpy.baseapp.home.page.HomeFragment
import com.wpy.baseapp.me.page.MeFragment
import com.wpy.core.mvvm.BaseBVMActivity


@Route(path = ARouterPath.ACTIVITY_MAIN)
class MainActivity : TabBtnClickCallback, BaseBVMActivity<ActivityMainBinding, MainActivityViewModel>() {

    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var fragmentManager: FragmentManager

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun createViewModel() = MainActivityViewModel()

    override fun initData() {
        super.initData()
        mViewModel.init()
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)

        // 初始化 FragmentManager
        fragmentManager = supportFragmentManager

        // 检查 Activity 是否是第一次创建（避免重叠问题）
        if (savedInstanceState == null) {
            // 如果是第一次创建，则添加首页 Fragment
            val initialFragment: Fragment = HomeFragment()
            addFragment(fragmentManager, initialFragment)
        }

        mBinding.tabLayout.setClickListener(this)
    }

    // 添加 Fragment 的方法
    private fun addFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        addToBackStack: Boolean = false
    ) {
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)

        // 如果需要添加到回退栈，则执行 addToBackStack 操作
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }

    override fun invoke(position: Int) {
        Log.i(TAG, "----- click position $position -----")
        if (mViewModel.mCurrentTab != position && position != 2) {
            when (position) {
                0 -> {
                    addFragment(fragmentManager, HomeFragment())
                    window.statusBarColor = ContextCompat.getColor(this, android.R.color.white)
                }
                1 -> {
//                    addFragment(fragmentManager, MessageFragment())
//                    window.statusBarColor = ContextCompat.getColor(this, android.R.color.white)
                }
                2 -> {
//                    addFragment(fragmentManager, MoreFragment())
//                    window.statusBarColor = ContextCompat.getColor(this, R.color.background)
                }
                3 -> {
                    addFragment(fragmentManager, MeFragment())
                }
            }
            mViewModel.mCurrentTab = position
        }
    }
}