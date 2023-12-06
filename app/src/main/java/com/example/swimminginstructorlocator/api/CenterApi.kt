package com.example.swimminginstructorlocator.api

import com.example.swimminginstructorlocator.data.model.Center
import com.example.swimminginstructorlocator.data.model.CenterDetail
import com.example.swimminginstructorlocator.data.model.Course
import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Url

interface CenterApi {
    data class ApiResponse(
        @SerializedName("message")
        val message: String,
        @SerializedName("data")
        val data: MutableList<Center>
    )

    data class SearchApiResponse(
        @SerializedName("message")
        val message: String,
        @SerializedName("data")
        val data: SearchApiData
    )

    data class SearchApiData(
        @SerializedName("centers")
        val centers: MutableList<Center>
    )

    data class ApiDetailResponse(
        @SerializedName("message")
        val message: String,
        @SerializedName("data")
        val data: CenterDetail
    )

    data class ListCourseResponse(
        @SerializedName("message")
        val message: String,
        @SerializedName("data")
        val data: MutableList<Course>
    )

    @GET("center/all")
    fun getCenters(): Call<ApiResponse>

    @GET
    fun getCenterDetail(@Url id: String): Call<ApiDetailResponse>

    @GET
    fun searchCenters(@Url searchValue: String): Call<SearchApiResponse>

    @GET
    fun getCourseOfInstructor(@Url instructorId: String): Call<ListCourseResponse>
}