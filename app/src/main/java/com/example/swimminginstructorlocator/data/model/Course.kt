package com.example.swimminginstructorlocator.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Course(
    @SerializedName("_id")
    val id: String,
    @SerializedName("course_name")
    val courseName: String,
    val description: String,
    var image: String,
    val schedule: String,
    @SerializedName("number_of_students")
    val numberOfStudents: Int,
    @SerializedName("teacher_id")
    val instructor: Instructor
) : Parcelable