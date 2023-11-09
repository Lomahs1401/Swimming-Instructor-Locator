package com.example.swimminginstructorlocator.api

import com.example.swimminginstructorlocator.data.model.Instructor
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface InstructorApi {

    data class ApiResponse(
        val message: String,
        val data: MutableList<Instructor>
    )

    data class SearchApiResponse(
        val message: String,
        val data: SearchApiData
    )

    data class SearchApiData(
        val teachers: MutableList<Instructor>
    )

    @GET("teacher")
    fun getInstructors(): Call<ApiResponse>

    @GET
    fun searchInstructors(@Url searchValue: String): Call<SearchApiResponse>
}