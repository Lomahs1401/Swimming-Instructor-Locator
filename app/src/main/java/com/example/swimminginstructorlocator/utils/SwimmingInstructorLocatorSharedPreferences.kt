package com.example.swimminginstructorlocator.utils

import android.content.Context

class SwimmingInstructorLocatorSharedPreferences(private val context: Context) {
    fun putBooleanValue(key: String, value: Boolean) {
        val sharedPreferences =
            context.getSharedPreferences(
                SWIMMING_INSTRUCTOR_LOCATOR_SHARED_PREFERENCES,
                Context.MODE_PRIVATE
            )

        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBooleanValue(key: String): Boolean {
        val sharedPreferences =
            context.getSharedPreferences(
                SWIMMING_INSTRUCTOR_LOCATOR_SHARED_PREFERENCES,
                Context.MODE_PRIVATE
            )

        return sharedPreferences.getBoolean(key, false)
    }

    fun putStringValue(key: String, value: String) {
        val sharedPreferences =
            context.getSharedPreferences(
                SWIMMING_INSTRUCTOR_LOCATOR_SHARED_PREFERENCES,
                Context.MODE_PRIVATE
            )
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getStringValue(key: String): String {
        val sharedPreferences =
            context.getSharedPreferences(
                SWIMMING_INSTRUCTOR_LOCATOR_SHARED_PREFERENCES,
                Context.MODE_PRIVATE
            )

        return sharedPreferences.getString(key, "").toString()
    }

    companion object {
        private const val SWIMMING_INSTRUCTOR_LOCATOR_SHARED_PREFERENCES =
            "SWIMMING_INSTRUCTOR_LOCATOR_SHARED_PREFERENCES"
    }
}
