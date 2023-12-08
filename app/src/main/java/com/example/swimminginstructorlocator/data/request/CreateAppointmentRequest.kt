package com.example.swimminginstructorlocator.data.request

import com.google.gson.annotations.SerializedName

data class CreateAppointmentRequest(
    @SerializedName("instructor") val instructorId: String,
    @SerializedName("student") val studentId: String,
    @SerializedName("course") val courseId: String,
    @SerializedName("date") val date: String,
    @SerializedName("startTime") val startTime: String,
    @SerializedName("endTime") val endTime: String,
    @SerializedName("status") val status: String
)