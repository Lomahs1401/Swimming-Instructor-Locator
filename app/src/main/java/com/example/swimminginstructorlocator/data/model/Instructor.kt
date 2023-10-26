package com.example.swimminginstructorlocator.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Instructor(
    val name: String,
    val avatar: String,
    val email: String,
    val gender: Int,
    val address: String,
    val phone: String,
    val weight: String,
    val height: String,
    val id: String
) : Parcelable