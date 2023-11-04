package com.example.swimminginstructorlocator.api

import com.example.swimminginstructorlocator.data.model.Instructor
import retrofit2.Call
import retrofit2.http.GET

interface InstructorApi {

    data class ApiResponse(
        val message: String,
        val data: MutableList<Instructor>
    )

    @GET("teacher")
    fun getInstructors(): Call<ApiResponse>

}