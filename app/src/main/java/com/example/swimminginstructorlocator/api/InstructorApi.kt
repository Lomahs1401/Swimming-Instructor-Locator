package com.example.swimminginstructorlocator.api

import com.example.swimminginstructorlocator.data.model.Course
import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.data.model.InstructorDetail
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface InstructorApi {

    data class ApiResponse(
        @SerializedName("message")
        val message: String,
        val data: MutableList<Instructor>
    )

    data class InstructorDetailApiResponse(
        @SerializedName("message")
        val message: String,
        @SerializedName("data")
        val data: InstructorDetail
    )

    data class SearchApiResponse(
        @SerializedName("message")
        val message: String,
        @SerializedName("data")
        val data: SearchApiData
    )

    data class SearchApiData(
        @SerializedName("teachers")
        val teachers: MutableList<Instructor>
    )

    data class ListCourseResponse(
        @SerializedName("message")
        val message: String,
        @SerializedName("data")
        val data: MutableList<Course>
    )

    @GET("teacher")
    fun getInstructors(): Call<ApiResponse>

    @GET
    fun getInstructorDetail(@Url id: String): Call<InstructorDetailApiResponse>

    @GET
    fun searchInstructors(@Url searchValue: String): Call<SearchApiResponse>

    @GET
    fun getListCourse(@Url instructorId: String): Call<ListCourseResponse>
}