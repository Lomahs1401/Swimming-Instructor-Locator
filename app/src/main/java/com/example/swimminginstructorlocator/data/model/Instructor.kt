package com.example.swimminginstructorlocator.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
@Parcelize
data class Instructor(
    @SerializedName("_id")
    val id: String,
    @SerializedName("teacher_name")
    val instructorName: String,
    var image: String,
    val description: String,
    val certificate: String,
    val graduate: String,
    @SerializedName("experience")
    val yearExperiences: Int,
) : Parcelable