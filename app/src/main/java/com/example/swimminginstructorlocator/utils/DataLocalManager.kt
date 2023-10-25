package com.example.swimminginstructorlocator.utils

import android.content.Context

class DataLocalManager {
    private lateinit var swimmingInstructorLocatorSharedPreferences: SwimmingInstructorLocatorSharedPreferences

    companion object {
        private const val SHARED_PREFERENCES_FIRST_INSTALL_KEY = "FIRST_INSTALL"

        private var instance: DataLocalManager? = null

        fun init(context: Context) {
            instance = DataLocalManager()
            instance?.swimmingInstructorLocatorSharedPreferences =
                SwimmingInstructorLocatorSharedPreferences(context)
        }

        private fun getInstance(): DataLocalManager {
            if (instance == null) {
                instance = DataLocalManager()
            }
            return instance as DataLocalManager
        }

        fun setFirstInstalled(isFirstTimeInstalled: Boolean) {
            getInstance().swimmingInstructorLocatorSharedPreferences.putBooleanValue(
                SHARED_PREFERENCES_FIRST_INSTALL_KEY, isFirstTimeInstalled
            )
        }

        fun getFirstInstalled(): Boolean {
            return getInstance().swimmingInstructorLocatorSharedPreferences.getBooleanValue(
                SHARED_PREFERENCES_FIRST_INSTALL_KEY
            )
        }
    }
}
