package com.example.swimminginstructorlocator.data.repo

import android.util.Log
import com.example.swimminginstructorlocator.api.CenterApi
import com.example.swimminginstructorlocator.constant.Constant
import com.example.swimminginstructorlocator.data.model.Center
import com.example.swimminginstructorlocator.data.model.User
import com.example.swimminginstructorlocator.listener.OnResultListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserRepo {
    fun register(username: String, email: String, password: String, listener: OnResultListener<User>) {

    }

    fun login(email: String, password: String, listener: OnResultListener<User>) {

    }


    companion object {
        private var instance: UserRepo? = null

        fun getInstance(): UserRepo {
            return synchronized(this) {
                instance ?: UserRepo().also {
                    instance = it
                }
            }
        }
    }
}