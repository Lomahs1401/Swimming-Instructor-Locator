package com.example.swimminginstructorlocator.api

import retrofit2.Call
import com.example.swimminginstructorlocator.data.model.Appointment
import retrofit2.http.Url
import retrofit2.http.GET
import com.google.gson.annotations.SerializedName
import retrofit2.http.DELETE
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

    @GET
    fun getAppointmentByUserId(@Url userId: String): Call<ApiResponse>

    @DELETE
    fun deleteAppointment(@Url appointmentId: String): Call<ApiResponseDelete>

}