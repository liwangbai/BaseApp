package com.wpy.core.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Wangbai.
 * Date: 2023-07-18 00:18:00
 * Desc:
 */
abstract class BaseBVMFragment<B: ViewDataBinding, VM: BaseViewModel>: BaseFragment<B>() {

    lateinit var mViewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewModel = ViewModelProvider(this).get(createViewModel()::class.java)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    protected abstract fun createViewModel(): VM
}