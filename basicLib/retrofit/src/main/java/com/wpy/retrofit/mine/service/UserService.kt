package com.wpy.retrofit.mine.service


import com.wpy.retrofit.mine.bean.UserResp
import retrofit2.Call
import retrofit2.http.POST

interface UserService {
    @POST("api/v1/loan")
    fun loginByPhone(): Call<UserResp>
}