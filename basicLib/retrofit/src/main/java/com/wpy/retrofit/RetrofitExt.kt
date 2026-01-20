package com.wpy.retrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> Call<T>.api(
    success: ((sucCall: Call<T>, response: Response<T>) -> Unit),
    failed: ((failedCall: Call<T>, t: Throwable) -> Unit) = {_, t ->
        t.printStackTrace()
    }
) {
    this.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            success(call, response)
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            failed(call, t)
        }

    })
}