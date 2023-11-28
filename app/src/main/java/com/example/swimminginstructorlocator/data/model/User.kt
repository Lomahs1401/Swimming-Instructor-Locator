package com.example.swimminginstructorlocator.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @SerializedName("_id")
    val id: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("fullname")
    val fullName: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("avatar")
    var avatar: String?,
    @SerializedName("gender")
    val gender: Int?,
    @SerializedName("address")
    val address: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("type")
    val type: Int?,
    @SerializedName("weight")
    val weight: String?,
    @SerializedName("height")
    val height: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?
) : Parcelable