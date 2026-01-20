package com.wpy.core.mvvm

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

abstract class BaseBVMActivity<B :ViewDataBinding, VM: BaseViewModel> : BaseBindActivity<B>() {

    lateinit var mViewModel: VM

    override fun initFramework() {
        super.initFramework()
        initViewModel()
    }

    private fun initViewModel() {
        mViewModel = ViewModelProvider(this).get(createViewModel()::class.java)
    }

    protected abstract fun createViewModel(): VM
}