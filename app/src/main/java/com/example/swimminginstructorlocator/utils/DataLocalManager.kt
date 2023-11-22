package com.example.swimminginstructorlocator.utils

import android.content.Context
import com.example.swimminginstructorlocator.data.model.User

class DataLocalManager {
    private lateinit var swimmingInstructorLocatorSharedPreferences: SwimmingInstructorLocatorSharedPreferences

    companion object {
        private const val SHARED_PREFERENCES_FIRST_INSTALL_KEY = "FIRST_INSTALL"
        private const val SHARED_PREFERENCES_CREATE_SESSION = "CREATE_SESSION"

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

        fun getUser(): User? {
            return getInstance().swimmingInstructorLocatorSharedPreferences.getUser(
                SHARED_PREFERENCES_CREATE_SESSION
            )
        }

        fun saveUser(user: User) {
            return getInstance().swimmingInstructorLocatorSharedPreferences.saveUser(
                SHARED_PREFERENCES_CREATE_SESSION,
                user
            )
        }

        fun removeUser() {
            return getInstance().swimmingInstructorLocatorSharedPreferences.clearUser(
                SHARED_PREFERENCES_CREATE_SESSION
            )
        }
    }
}
