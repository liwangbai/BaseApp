package com.wpy.utils

import android.content.Context
import com.google.gson.Gson
import com.wpy.utils.ext.isNotNullOrBlank

object SharedPreferencesUtil {

    const val SPLIT_CHAR = "___"
    // 这里app取的AppUtils中的lateinit var值，所以必须保证在SharedPreferencesUtil调用之前，AppUtils
    // 已经被调用过（kotlin的object类在初次被调用的时候初始化），因此在Application的onCreate中调用了
    // AppUtils的init方法，且在此之前不能调用SharedPreferencesUtil中的方法。
    private val sp = AppUtils.app.getSharedPreferences("common_file", Context.MODE_PRIVATE)

    /**
     * 保存数据到SharedPreferences
     *
     * @param key   键
     * @param value 需要保存的数据
     * @return 保存结果
     */
    fun putSPData(key: String, value: Any): Boolean {
        val type = value.javaClass.simpleName

        var result: Boolean
        try {
            val editor = sp.edit()
            when (type) {
                "Boolean" -> editor.putBoolean(key, value as Boolean)
                "Long" -> editor.putLong(key, value as Long)
                "Float" -> editor.putFloat(key, value as Float)
                "Integer" -> editor.putInt(key, value as Int)
                "String" -> editor.putString(key, value as String)
                else -> {
                    editor.putString(key, Gson().toJson(value))
                }
            }
            editor.apply()

            result = true
        } catch (e: Exception) {
            e.printStackTrace()
            result = false
        }

        return result
    }

    /**
     * 获取SharedPreferences中保存的数据
     *
     * @param key          键
     * @param defaultValue 获取失败默认值
     * @return 从SharedPreferences读取的数据
     */
    fun getSPData(key: String, defaultValue: Any): Any? {
        val type = defaultValue.javaClass.simpleName
        var result: Any?
        try {
            result = when (type) {
                "Boolean" -> sp.getBoolean(key, defaultValue as Boolean)
                "Long" -> sp.getLong(key, defaultValue as Long)
                "Float" -> sp.getFloat(key, defaultValue as Float)
                "Integer" -> sp.getInt(key, defaultValue as Int)
                "String" -> sp.getString(key, defaultValue as String)
                else -> {
                    val json = sp.getString(key, "")
                    if (json.isNotNullOrBlank()) {
                        Gson().fromJson(json, defaultValue.javaClass)
                    } else {
                        defaultValue
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            result = defaultValue
        }

        return result
    }
}