package com.example.swimminginstructorlocator.api

import com.example.swimminginstructorlocator.constant.Constant
import com.example.swimminginstructorlocator.data.model.User
import com.example.swimminginstructorlocator.data.request.LoginRequest
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    data class ApiResponse(
        val message: String,
        val user: User
    )
    @POST("auth/login")
    fun login(@Body loginRequest : LoginRequest): Call<ApiResponse>
}