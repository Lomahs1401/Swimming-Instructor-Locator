package com.example.swimminginstructorlocator.data.repo

import android.content.ContentResolver
import android.util.Log
import com.example.swimminginstructorlocator.api.InstructorApi
import com.example.swimminginstructorlocator.constant.Constant
import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.data.service.impl.InstructorServiceImpl
import com.example.swimminginstructorlocator.listener.OnResultListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

class InstructorRepo private constructor() {

    @GET("/user")
    fun getInstructors() {
        val api = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(InstructorApi::class.java)

        api.getInstructors().enqueue(object : Callback<MutableList<Instructor>> {
            override fun onResponse(
                call: Call<MutableList<Instructor>>,
                response: Response<MutableList<Instructor>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        for (instructor in it) {
                            Log.i(Constant.TAG, "onResponse: ${instructor.name}")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<MutableList<Instructor>>, t: Throwable) {
                Log.e(Constant.TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private var instance: InstructorRepo? = null

        fun getInstance(): InstructorRepo {
            return synchronized(this) {
                instance ?: InstructorRepo().also {
                    instance = it
                }
            }
        }
    }
}