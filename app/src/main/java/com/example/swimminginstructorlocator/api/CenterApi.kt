package com.example.swimminginstructorlocator.api

import com.example.swimminginstructorlocator.data.model.Center
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Url

interface CenterApi {
    data class ApiResponse(
        val message: String,
        val data: MutableList<Center>
    )

    data class SearchApiResponse(
        val message: String,
        val data: SearchApiData
    )

    data class SearchApiData(
        val centers: MutableList<Center>
    )

    @GET("center/all")
    fun getCenters(): Call<ApiResponse>

    @GET
    fun searchCenters(@Url searchValue: String): Call<SearchApiResponse>
}