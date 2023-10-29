package com.example.swimminginstructorlocator.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String,
    @SerializedName("name")
    val username: String,
    val password: String,
    val avatar: String,
    val email: String,
    @SerializedName("sex")
    val gender: Int,
    val address: String,
    val phone: String,
    val type: String,
    val weight: String,
    val height: String,
) : Parcelable