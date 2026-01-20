package com.wpy.arouter

object ARouterPath {

    // 是否使用拦截器
    // const val IS_USE_INTERCEPTOR = true

    // ====== 分Module ======
    // !!!!!! 同一个Module下的路径，/aaa/xxx 中，aaa必须相同，否则多模块配置会失效 !!!!!!
    // app
    const val ACTIVITY_DEV_TEST = "/app/TestActivity"   // 开发阶段功能测试页
    const val ACTIVITY_SPLASH = "/app/SplashActivity"   // 闪屏页
    const val ACTIVITY_MAIN = "/app/HomeActivity"   // 主Activity

    // business:home
    const val ACTIVITY_SEARCH = "/home/SearchActivity"

    // business:message

    // business:create
    const val ACTIVITY_CREATE = "/create/CreateActivity"

    // business:more

    // business:me



}