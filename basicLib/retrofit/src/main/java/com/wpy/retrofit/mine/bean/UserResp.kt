package com.wpy.retrofit.mine.bean

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.wpy.retrofit.BaseResponse

@Keep
class UserResp: BaseResponse<UserResp.Data>() {
    data class Data (
        @SerializedName("custId")
        val uId: String,
        @SerializedName("userName")
        val userName: String,
        @SerializedName("password")
        val password: String,
        @SerializedName("phone")
        val phone: String
    )
}