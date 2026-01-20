package com.wpy.retrofit

import com.google.gson.annotations.SerializedName

open class BaseResponse<T>() {
    @SerializedName("code")
    val code: Int = 0 // 0

    @SerializedName("msg")
    val msg: String = "" // ok

    @SerializedName("data")
    val data: T? = null

    override fun toString(): String {
        return "BaseResponse(code=$code, msg='$msg', `data`=$`data`)"
    }
}