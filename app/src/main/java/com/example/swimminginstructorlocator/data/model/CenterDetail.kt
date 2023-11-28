package com.example.swimminginstructorlocator.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CenterDetail(
    @SerializedName("_id")
    val id: String,
    @SerializedName("center_name")
    val centerName: String,
    @SerializedName("manager")
    val managerId: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    var image: String,
    @SerializedName("phone_number")
    val phone: String,
    @SerializedName("teachers")
    val instructors: MutableList<Instructor>
) : Parcelable