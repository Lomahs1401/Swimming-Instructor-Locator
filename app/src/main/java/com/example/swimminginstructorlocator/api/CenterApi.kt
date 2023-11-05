package com.example.swimminginstructorlocator.api

import com.example.swimminginstructorlocator.data.model.Center
import retrofit2.http.GET
import retrofit2.Call

interface CenterApi {
    data class ApiResponse(
        val message: String,
        val data: MutableList<Center>
    )
    @GET("center/all")
    fun getCenters(): Call<ApiResponse>
}