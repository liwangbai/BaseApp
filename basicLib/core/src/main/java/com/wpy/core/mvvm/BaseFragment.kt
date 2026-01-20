package com.wpy.core.mvvm

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


/**
 * Created by Wangbai.
 * Date: 2023-07-17 23:15:00
 * Desc:
 */
abstract class BaseFragment<B: ViewDataBinding>: Fragment() {

    protected lateinit var mBinding: B
    private var rootView: View? = null
    private lateinit var mContext: Context


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (null == rootView) {
            //如果缓存中有rootView则直接使用
            initViewDataBinding(inflater, container)
            this.rootView = mBinding.root;
        } else {
            rootView?.let {
                it.parent?.let { it2 -> (it2 as ViewGroup).removeView(it) }
            }
        }
        return rootView
    }

    private fun initViewDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        //支持LiveData绑定xml，数据改变，UI自动会更新
        mBinding.lifecycleOwner = this
    }

    open fun getRootView(): View? = rootView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onDestroyView() {
        mBinding.unbind()
        super.onDestroyView()
    }

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //在OnCreate方法中调用下面方法，然后再使用线程，就能在uncaughtException方法中捕获到异常
        initData(savedInstanceState)
        initView(savedInstanceState)
    }

    abstract fun initData(savedInstanceState: Bundle?)

    abstract fun initView(savedInstanceState: Bundle?)
}