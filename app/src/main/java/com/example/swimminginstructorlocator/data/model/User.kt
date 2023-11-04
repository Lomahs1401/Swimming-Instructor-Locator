package com.example.swimminginstructorlocator.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @SerializedName("_id")
    val id: String,
    val username: String,
    val password: String,
    var avatar: String,
    val email: String,
    val gender: Int,
    val address: String,
    val phone: String,
    val type: Int,
    val weight: String,
    val height: String,
) : Parcelable