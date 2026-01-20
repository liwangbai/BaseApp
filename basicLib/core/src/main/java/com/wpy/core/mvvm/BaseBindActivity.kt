package com.wpy.core.mvvm

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created by Wangbai.
 * Date: 2023-07-14 23:20:00
 * Desc:
 */
abstract class BaseBindActivity<B: ViewDataBinding>: BaseActivity() {

    protected open lateinit var mRootView: ViewGroup
    protected open lateinit var mContainerView: FrameLayout
    protected open var mToolBar: Toolbar? = null

    protected lateinit var mBinding: B

    override fun initFramework() {
        super.initFramework()
        initDataBinding()
    }

    private fun initDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
    }

    override fun onDestroy() {
        mBinding.unbind()
        super.onDestroy()
    }
}