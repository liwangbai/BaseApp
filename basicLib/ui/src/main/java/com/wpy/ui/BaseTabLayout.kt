package com.wpy.ui

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.tabs.TabLayout

/**
 * 自定义顶部tab栏
 */
class BaseTabLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TabLayout(context, attrs, defStyleAttr){
    init {
        // 在这里进行初始化操作，例如设置样式等
    }
}