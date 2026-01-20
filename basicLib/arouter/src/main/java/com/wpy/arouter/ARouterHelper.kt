package com.wpy.arouter

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavCallback
import com.alibaba.android.arouter.facade.template.IProvider
import com.alibaba.android.arouter.launcher.ARouter


object ARouterHelper {

    const val TAG = "ARouterHelper"

    fun initARouter(application: Application) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, "===== DEBUG Env =====")
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(application)
    }

    fun injectARouter(thiz: Any) {
        ARouter.getInstance().inject(thiz)
    }

    // 不带参跳转
    fun simpleJump(path: String, flag: Int? = null) {
        if (flag != null) {
            ARouter.getInstance().build(path).withFlags(flag).navigation()
        }else {
            ARouter.getInstance().build(path).navigation()
        }
    }

    /**
     * 跳转到指定路径，并携带参数
     * @param path 路由路径
     * @param params 参数键值对
     * @param flag 传统跳转的Intent的flag参数(java调用如没这个参数传null)
     */
    fun jumpWithParams(path: String, params: Map<String, Any?>, flag: Int? = null, enterAnim: Int? = null, exitAnim: Int? = null) {
        val postcard = ARouter.getInstance().build(path)
        params.forEach { (key, value) ->
            when (value) {
                is Int -> postcard.withInt(key, value)
                is String -> postcard.withString(key, value)
                is Boolean -> postcard.withBoolean(key, value)
                is Long -> postcard.withLong(key, value)
                is Double -> postcard.withDouble(key, value)
                is Float -> postcard.withFloat(key, value)
                is Bundle -> postcard.withBundle(key, value)
                else -> throw IllegalArgumentException("Unsupported type for ARouter params.")
            }
        }
        if (flag != null) {
            if (enterAnim != null && exitAnim != null){
                postcard.withFlags(flag).withTransition(enterAnim, exitAnim).navigation()
            }else {
                postcard.withFlags(flag).navigation()
            }
        }else {
            postcard.navigation()
        }
    }

    /**
     * 跳转并监听结果
     * @param context 当前上下文
     * @param path 路由路径
     * @param onArrival 成功回调
     * @param onInterrupt 拦截回调
     */
    fun navigateWithCallback(
        context: Context,
        path: String,
        onArrival: () -> Unit = {},
        onInterrupt: () -> Unit = {}
    ) {
        ARouter.getInstance()
            .build(path)
            .navigation(context, object : NavCallback() {
                override fun onArrival(postcard: Postcard?) {
                    onArrival()
                }

                override fun onInterrupt(postcard: Postcard?) {
                    onInterrupt()
                }
            })
    }

    /**
     * 获取Service的api
     *
     * @param clazz 需要获取的Service实现的接口
     * @return 返回的Service的实例，如没有匹配的则返回null
     */
    inline fun <reified T : IProvider> getService(path: String): T? {
        return try {
            val service = ARouter.getInstance().build(path).navigation()
            if (service is T) service else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * 获取Service的api
     *
     * @param clazz 需要获取的Service实现的接口
     * @return 返回的Service的实例，如没有匹配的则返回null
     */
    inline fun <reified T : IProvider> getServiceByClass(): T? {
        return try {
            ARouter.getInstance().navigation(T::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * 兼容java的获取Service的api
     *
     * @param clazz 需要获取的Service实现的接口
     * @return 返回的Service的实例，如没有匹配的则返回null
     */
    @JvmStatic
    fun <T : IProvider> getService(path: String, clazz: Class<T>): T? {
        return try {
            val service = ARouter.getInstance().build(path).navigation()
            if (clazz.isInstance(service)) clazz.cast(service) else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * 兼容java的获取Service的api
     *
     * @param clazz 需要获取的Service实现的接口
     * @return 返回的Service的实例，如没有匹配的则返回null
     */
    @JvmStatic
    fun <T : IProvider> getServiceByClass(clazz: Class<T>): T? {
        return try {
            ARouter.getInstance().navigation(clazz)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}