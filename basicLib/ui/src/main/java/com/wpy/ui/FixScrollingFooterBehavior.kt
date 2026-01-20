package com.wpy.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout

class FixScrollingFooterBehavior @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
): AppBarLayout.ScrollingViewBehavior(context, attrs) {
    private var appBarLayout: AppBarLayout? = null

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        if (appBarLayout == null && dependency is AppBarLayout){
            appBarLayout = dependency
        }
        val result = super.onDependentViewChanged(parent, child, dependency)
        appBarLayout?.let {
            val bottomPadding = calculateBottomPadding(it)
            val paddingChanged = bottomPadding != child.paddingBottom
            if (paddingChanged){
                child.setPadding(
                    child.paddingLeft,
                    child.paddingTop,
                    child.paddingRight,
                    bottomPadding
                )
                child.requestLayout()
            }
            return paddingChanged || result
        }
        return result
    }

    private fun calculateBottomPadding(dependency: AppBarLayout): Int{
        val totalScrollRage = dependency.totalScrollRange
        return totalScrollRage + dependency.top
    }
}