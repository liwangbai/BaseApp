package com.wpy.me.page

import android.os.Bundle
import com.wpy.me.viewmodel.MeFragmentViewModel
import com.wpy.core.mvvm.BaseBVMFragment
import com.wpy.me.R
import com.wpy.me.databinding.FragmentMeBinding

/**
 * Created by Wangbai.
 * Date: 2023-07-16 20:24:00
 * Desc:
 */
class MeFragment: BaseBVMFragment<FragmentMeBinding, MeFragmentViewModel>() {

    override fun getLayoutId() = R.layout.fragment_me
    override fun createViewModel() =  MeFragmentViewModel()

    override fun initView(savedInstanceState: Bundle?) {
        setListener()
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    private fun setListener() {

    }

}