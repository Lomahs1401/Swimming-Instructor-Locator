package com.example.swimminginstructorlocator.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Appointment (
    @SerializedName("_id")
    val id: String,
    @SerializedName("course_name")
    val courseName: String,
    @SerializedName("teacher_name")
    val teacherName: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("startTime")
    val startTime: String,
    @SerializedName("endTime")
    val endTime: String,
    @SerializedName("student_name")
    val studentName: String,
    @SerializedName("center_address")
    val centerAddress: String,
    @SerializedName("status")
    val status: String,
) : Parcelable