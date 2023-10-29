package com.example.swimminginstructorlocator.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Instructor(
    @SerializedName("_id")
    val id: String,
    val name: String,
    val avatar: String,
    val email: String,
    val gender: Int,
    val address: String,
    val phone: String,
    val weight: String,
    val height: String,
) : Parcelable