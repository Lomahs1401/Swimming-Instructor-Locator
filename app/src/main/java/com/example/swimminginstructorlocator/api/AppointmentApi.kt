package com.example.swimminginstructorlocator.api

import retrofit2.Call
import com.example.swimminginstructorlocator.data.model.Appointment
import com.example.swimminginstructorlocator.data.request.CreateAppointmentRequest
import retrofit2.http.Url
import retrofit2.http.GET
import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface AppointmentApi {
    data class ApiResponse(
        @SerializedName("message")
        val message: String,
        @SerializedName("data")
        val data: MutableList<Appointment>
    )
    data class ApiResponseDelete(
        @SerializedName("message")
        val message: String
    )
    data class CreateResponse(
        @SerializedName("message")
        val message: String,
        @SerializedName("data")
        val data: Appointment
    )
    @GET
    fun getAppointmentByUserId(@Url userId: String): Call<ApiResponse>

    @POST("appoiment/create")
    fun createAppointment(@Body request: CreateAppointmentRequest): Call<CreateResponse>

    @DELETE
    fun deleteAppointment(@Url appointmentId: String): Call<ApiResponseDelete>

}