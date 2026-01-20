package com.wpy.home.page

import android.os.Bundle
import com.wpy.home.viewmodel.HomeFragmentViewModel
import com.wpy.core.mvvm.BaseBVMFragment
import com.wpy.home.R
import com.wpy.home.databinding.FragmentHomeBinding

/**
 * Created by Wangbai.
 * Date: 2023-07-16 20:25:00
 * Desc:
 */
class HomeFragment : BaseBVMFragment<FragmentHomeBinding, HomeFragmentViewModel>() {

    override fun getLayoutId() = R.layout.fragment_home
    override fun createViewModel() = HomeFragmentViewModel()

    override fun initView(savedInstanceState: Bundle?) {

        initListener()
    }

    /**
     * 初始化数据，进入首页，ViewModel中调接口，拉去首批推荐数据
     */
    override fun initData(savedInstanceState: Bundle?) {

    }

    /**
     * 设置首页各按钮点击事件
     */
    private fun initListener() {

    }

}