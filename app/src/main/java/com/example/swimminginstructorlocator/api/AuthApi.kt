package com.example.swimminginstructorlocator.api

import com.example.swimminginstructorlocator.data.model.User
import com.example.swimminginstructorlocator.data.request.LoginRequest
import com.example.swimminginstructorlocator.data.request.RegisterRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    data class ApiSuccessResponse(
        val message: String,
        val user: User
    )

    data class ApiErrorResponse(
        val error: String
    )

    @POST("auth/login")
    fun login(@Body loginRequest : LoginRequest): Call<ApiSuccessResponse>

    @POST("auth/register")
    fun register(@Body registerRequest: RegisterRequest): Call<ApiSuccessResponse>
}