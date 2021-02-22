package com.sotcha.weather.data.utils

import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Helping utility which stores data to shared prederences
 *
 */
class SharedPrefsHelper @Inject constructor(private val sharedPreferences: SharedPreferences) {
    fun put(key: String?, value: String?) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun put(key: String?, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun put(key: String?, value: Float) {
        sharedPreferences.edit().putFloat(key, value).apply()
    }

    fun put(key: String?, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }

    fun put(key: String?, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    operator fun get(key: String?, defaultValue: String?): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    operator fun get(key: String?, defaultValue: Int): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    operator fun get(key: String?, defaultValue: Long): Long {
        return sharedPreferences.getLong(key, defaultValue)
    }

    operator fun get(key: String?, defaultValue: Float): Float {
        return sharedPreferences.getFloat(key, defaultValue)
    }

    operator fun get(key: String?, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    fun removeKey(key: String?) {
        sharedPreferences.edit().remove(key).apply()
    }

    fun hasKey(key: String?): Boolean {
        return sharedPreferences.contains(key)
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        const val PREF_NAME = "app_default_pref"
    }

}