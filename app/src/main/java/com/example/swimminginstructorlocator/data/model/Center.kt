package com.example.swimminginstructorlocator.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Center(
    @SerializedName("_id")
    val id: String,
    @SerializedName("center_name")
    val centerName: String,
    @SerializedName("manager")
    val managerId: User,
    val email: String,
    val address: String,
    val description: String,
    var image: String,
    @SerializedName("phone_number")
    val phone: String,
) : Parcelable