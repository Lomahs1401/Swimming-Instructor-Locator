package com.example.swimminginstructorlocator.api

import com.example.swimminginstructorlocator.data.model.Course
import com.example.swimminginstructorlocator.data.model.CourseDetail
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface CourseApi {
    data class ApiDetailResponse(
        @SerializedName("message")
        val message: String,
        @SerializedName("data")
        val data: CourseDetail
    )

    @GET
    fun getCourseDetail(@Url id: String): Call<ApiDetailResponse>
}