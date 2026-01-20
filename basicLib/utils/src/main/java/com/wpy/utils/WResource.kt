package com.wpy.utils

/**
 * Created by Wangbai.
 * Date: 2023-07-16 20:47:00
 * Desc:
 */
object WResource {

    fun getString(strId: Int): String {
        return AppUtils.app.getString(strId)
    }

}