package com.example.swimminginstructorlocator.api

import com.example.swimminginstructorlocator.constant.Constant
import com.example.swimminginstructorlocator.data.model.User
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface UserApi {

    data class ApiResponse(
        val message: String,
        val user: MutableList<User>
    )

    companion object {
        private val gson: Gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create()

        val userApi: UserApi = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(UserApi::class.java)
    }

    @GET("user")
    fun getUsers(): Call<ApiResponse>
}