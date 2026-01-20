package com.wpy.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL = "http://10.0.2.2/"
    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
        GsonConverterFactory.create()
    ).build()

    /**
     *
     * 使用方式，类似于：
     *
     *    private val userService = ServiceCreator.create<UserService>()
     *    viewModelScope.launch {
     *             userService.loginByPhone().api(
     *                 success = { _, res ->
     *                     val body = res.body()
     *                     Log.i(TAG, "userinfo is ===> ${body.toString()}")
     *                 },
     *                 failed = {_, _ ->
     *
     *                 }
     *             )
     *
     *         }
     *
     */
    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
    inline fun <reified T> create(): T = create(T::class.java)
}
