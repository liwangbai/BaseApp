package com.wpy.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.alibaba.android.arouter.launcher.ARouter
import com.wpy.utils.ext.clickExt

/**
 * 自定义搜索框
 */
class BaseSearchView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    init {
        LayoutInflater.from(context).inflate(R.layout.search_view, this)
    }

    /**
     * 传入ARouter参数，设置点击搜索栏跳转的Activity
     */
    fun setClickTo(jumpPath: String){
        findViewById<LinearLayout>(R.id.container).clickExt {
            ARouter.getInstance().build(jumpPath).navigation()
        }
    }

}