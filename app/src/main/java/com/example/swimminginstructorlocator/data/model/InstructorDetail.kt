package com.example.swimminginstructorlocator.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class InstructorDetail(
    @SerializedName("_id")
    val id: String,
    val age: Int,
    @SerializedName("teacher_name")
    val instructorName: String,
    val description: String,
    val certificate: String,
    val graduate: String,
    @SerializedName("experience")
    val yearExperiences: Int,
    var image: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("center_id")
    val centerId: String,
    val username: String,
    val email: String,
    val gender: Int,
    var address: String,
    var avatar: String,
    val phone: String,
    val type: Int,
    val centerName: String,
) : Parcelable