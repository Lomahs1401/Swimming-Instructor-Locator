package com.example.swimminginstructorlocator.utils

import android.content.Context
import com.example.swimminginstructorlocator.data.model.User

class DataLocalManager {
    private lateinit var swimmingInstructorLocatorSharedPreferences: SwimmingInstructorLocatorSharedPreferences

    companion object {
        const val SHARED_PREFERENCES_FIRST_INSTALL_KEY = "FIRST_INSTALL"
        const val SHARED_PREFERENCES_LOGIN_SESSION = "LOGIN_SESSION"
        const val SHARED_PREFERENCES_USER_INFO = "USER_INFO"
        const val SHARED_PREFERENCES_INSTALL_STATUS = "INSTALL_STATUS"

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

        fun setIsLoggedIn(value: Boolean) {
            getInstance().swimmingInstructorLocatorSharedPreferences.setIsLoggedIn(
                SHARED_PREFERENCES_LOGIN_SESSION, value
            )
        }

        fun isLoggedIn(): Boolean {
            return getInstance().swimmingInstructorLocatorSharedPreferences.isLoggedIn(
                SHARED_PREFERENCES_LOGIN_SESSION
            )
        }

        fun getUser(): User? {
            return getInstance().swimmingInstructorLocatorSharedPreferences.getUser(
                SHARED_PREFERENCES_USER_INFO
            )
        }

        fun saveUser(user: User) {
            getInstance().swimmingInstructorLocatorSharedPreferences.saveUser(
                SHARED_PREFERENCES_USER_INFO,
                user
            )
        }

        fun removeUser() {
            getInstance().swimmingInstructorLocatorSharedPreferences.clearUser()
        }
    }
}
