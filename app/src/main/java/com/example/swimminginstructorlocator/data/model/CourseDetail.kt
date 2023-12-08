package com.example.swimminginstructorlocator.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CourseDetail(
    @SerializedName("_id")
    val id: String,
    @SerializedName("course_name")
    val courseName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    var image: String,
    @SerializedName("schedule")
    val schedule: String,
    @SerializedName("number_of_students")
    val numberOfStudents: Int,
    @SerializedName("price")
    val price: Int,
) : Parcelable