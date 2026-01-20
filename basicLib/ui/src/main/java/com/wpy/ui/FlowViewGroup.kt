package com.wpy.ui

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

/**
 * Created by wpy on 2024/10/31.
 */
class FlowViewGroup(context: Context,
                    attrs: AttributeSet? = null,
                    defStyleAttr: Int = 0) : ViewGroup(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMeasure = MeasureSpec.getSize(widthMeasureSpec)
        val highMeasure = MeasureSpec.getSize(heightMeasureSpec)

        // 实际设置最终测量宽高的值
        setMeasuredDimension(widthMeasure, highMeasure)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        TODO("Not yet implemented")
    }

}