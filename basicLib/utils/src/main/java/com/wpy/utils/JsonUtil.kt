package com.wpy.utils

import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.lang.Exception
import java.lang.reflect.Type

/**
 * Created by Wangbai.
 * Date: 2023-08-16 00:53:00
 * Desc: moshi封装json转换类
 */
class JsonUtil {
    companion object {
        const val TAG = "JsonUtil"
        val moshi: Moshi by lazy {
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        }
    }

    inline fun <reified T> toJson(t: T): String? {
        return try {
            val jsonAdapter: JsonAdapter<T> = moshi.adapter(T::class.java)
            jsonAdapter.toJson(t)
        }catch (e:Exception){
            Log.e(TAG, "toJson Exception:${e.printStackTrace()} --- ${T::class.java}")
            null
        }
    }

    inline fun <reified T> fromJson(json: String): T? {
        return try {
            val jsonAdapter: JsonAdapter<T> = moshi.adapter(T::class.java)
            jsonAdapter.fromJson(json)
        }catch (e:Exception){
            Log.e(TAG, "fromJson Exception:${e.printStackTrace()} --- ${T::class.java}")
            null
        }
    }

     inline fun <reified T> listToJson(list: List<T>?): String? {
        if (list == null) {
            Log.e(TAG, "listToJson(), list is empty,data: $list")
            return null
        }
        return try {
            val listAdapter: JsonAdapter<List<T>> = moshi.adapter(
                Types.newParameterizedType(
                    List::class.java,
                    T::class.java
                )
            )
            listAdapter.toJson(list)
        }catch (e:Exception){
            Log.e(TAG, "listToJson Exception:${e.printStackTrace()} --- ${T::class.java}")
            null
        }
    }

    inline fun <reified T> listFromJson(beanJson: String?): List<T>? {
        return listFromJson(beanJson, T::class.java)
    }

    inline fun <reified T> listFromJson(beanJson: String?, type: Type): List<T>? {
        return try {
            val listAdapter: JsonAdapter<List<T>> = moshi.adapter(
                Types.newParameterizedType(
                    List::class.java,
                    type
                )
            )
            listAdapter.fromJson(beanJson)
        }catch (e:Exception){
            Log.e(TAG, "listFromJson Exception:${e.printStackTrace()} --- ${type.typeName}")
            null
        }
    }

    inline fun <reified K, reified V> mapToJson(map: Map<K, V>?): String? {
        if (map == null) {
            Log.e(TAG, "mapToJson(), map is empty,data: $map")
            return null
        }
        return try {
            val type = Types.newParameterizedType(
                Map::class.java,
                K::class.java,
                V::class.java
            )
            val mapAdapter: JsonAdapter<Map<K, V>> = moshi.adapter(type)
            mapAdapter.toJson(map)
        }catch (e:Exception){
            Log.e(TAG, "mapToJson Exception:${e.printStackTrace()} --- $map")
            null
        }
    }


    inline fun <reified K, reified V> mapFromJson(beanJson: String?): Map<K, V>? {
        return try {
            val type = Types.newParameterizedType(
                Map::class.java,
                K::class.java,
                V::class.java
            )
            val mapAdapter: JsonAdapter<Map<K, V>> = moshi.adapter(type)
            mapAdapter.fromJson(beanJson)
        }catch (e:Exception){
            Log.e(TAG, "mapFromJson Exception:${e.printStackTrace()} --- $beanJson")
            null
        }
    }
}