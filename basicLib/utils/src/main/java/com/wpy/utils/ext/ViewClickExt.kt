package com.wpy.utils.ext

import android.view.View

/**
 * 设置点击监听, 并实现事件节流（500ms）
 */
var viewClickFlag = false
var clickRunnable = Runnable { viewClickFlag = false }
fun View.clickExt(action: (view: View) -> Unit) {
    setOnClickListener {
        if (!viewClickFlag) {
            viewClickFlag = true
            action(it)
        }
        removeCallbacks(clickRunnable)
        postDelayed(clickRunnable, 500)
    }
}

/**
 * 设置点击监听, 并实现事件节流
 */
fun View.clickOpt(continuousAction: ((view: View) -> Unit)? = null, action: (view: View) -> Unit) {
    val idKey = if (id == View.NO_ID) View.NO_ID else id // TODO
    val viewClickFlag = (getTag(idKey) as? Boolean) ?: false
    val clickRunnable = Runnable { setTag(idKey, false) }
    setOnClickListener {
        if (!viewClickFlag) {
            setTag(idKey, true)
            action(it)
        } else {
            continuousAction?.invoke(it)
        }
        removeCallbacks(clickRunnable)
        postDelayed(clickRunnable, 350)
    }
}

/**
 * 直接处理点击后的重复点击操作
 */
private var viewClickFlagN = false
private var clickRunnableN = Runnable { viewClickFlagN = false }
fun View.clickN(action: () -> Unit) {
    if (!viewClickFlagN) {
        viewClickFlagN = true
        action()
    }
    removeCallbacks(clickRunnableN)
    postDelayed(clickRunnableN, 500)
}