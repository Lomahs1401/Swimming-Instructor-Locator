package com.example.swimminginstructorlocator.utils

import android.content.Context
import com.example.swimminginstructorlocator.data.model.User

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

    fun saveUser(key: String, user: User) {
        val sharedPreferences =
            context.getSharedPreferences(
                SWIMMING_INSTRUCTOR_LOCATOR_SHARED_PREFERENCES,
                Context.MODE_PRIVATE
            )

        val editor = sharedPreferences.edit()
        editor.putString("id", user.id)
        editor.putString("username", user.username)
        editor.putString("email", user.email)
        editor.putString("avatar", user.avatar)
        user.gender?.let { editor.putInt("gender", it) }
        editor.putString("address", user.address)
        editor.putString("phone", user.phone)
        user.type?.let { editor.putInt("type", it) }
        editor.putString("weight", user.weight.toString())
        editor.putString("height", user.height.toString())
        editor.putString("createdAt", user.createdAt)
        editor.putString("updatedAt", user.updatedAt)
        editor.apply()
    }

    fun getUser(key: String): User?{
        val sharedPreferences =
            context.getSharedPreferences(
                SWIMMING_INSTRUCTOR_LOCATOR_SHARED_PREFERENCES,
                Context.MODE_PRIVATE
            )

        val id = sharedPreferences.getString("id", null)
        val fullName = sharedPreferences.getString("fullname", null)
        val username = sharedPreferences.getString("username", null)
        val email = sharedPreferences.getString("email", null)
        val avatar = sharedPreferences.getString("avatar", null)
        val gender = sharedPreferences.getInt("gender", 0)
        val address = sharedPreferences.getString("address", null)
        val phone = sharedPreferences.getString("phone", null)
        val type = sharedPreferences.getInt("type", 0)
        val weight = sharedPreferences.getString("weight", null)
        val height = sharedPreferences.getString("height", null)
        val createdAt = sharedPreferences.getString("createdAt", null)
        val updatedAt = sharedPreferences.getString("updatedAt", null)

        val user = User(id, username, fullName, email, avatar, gender,
            address, phone, type, weight, height, createdAt, updatedAt)

        return if (id != null && username != null) {
            user
        } else {
            null
        }
    }

    fun clearUser(key: String) {
        val sharedPreferences =
            context.getSharedPreferences(
                SWIMMING_INSTRUCTOR_LOCATOR_SHARED_PREFERENCES,
                Context.MODE_PRIVATE
            )

        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private const val SWIMMING_INSTRUCTOR_LOCATOR_SHARED_PREFERENCES =
            "SWIMMING_INSTRUCTOR_LOCATOR_SHARED_PREFERENCES"
    }
}
