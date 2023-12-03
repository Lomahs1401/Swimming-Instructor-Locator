package com.example.swimminginstructorlocator.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Appointment (
    @SerializedName("_id")
    val id: String,
    val instructor: User,
    val student: User,
    val course: Course,
    val date: String,
    val startTime: String,
    val endTime: String,
    val status: String,
) : Parcelable