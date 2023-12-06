package com.example.swimminginstructorlocator.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Appointment (
    @SerializedName("_id")
    val id: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("startTime")
    val startTime: String,
    @SerializedName("endTime")
    val endTime: String,
    @SerializedName("status")
    val status: String,
) : Parcelable