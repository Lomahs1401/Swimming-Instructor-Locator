package com.example.swimminginstructorlocator.api

import com.example.swimminginstructorlocator.data.model.Instructor
import retrofit2.Call
import retrofit2.http.GET

interface InstructorApi {
    @GET
    fun getInstructors(): Call<MutableList<Instructor>>

}