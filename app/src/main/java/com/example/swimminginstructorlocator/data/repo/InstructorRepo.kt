package com.example.swimminginstructorlocator.data.repo

import android.util.Log
import com.example.swimminginstructorlocator.api.InstructorApi
import com.example.swimminginstructorlocator.constant.Constant
import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.listener.OnResultListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InstructorRepo {

    fun getInstructors(listener: OnResultListener<MutableList<Instructor>>) {
        val api = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(InstructorApi::class.java)

        api.getInstructors().enqueue(object : Callback<InstructorApi.ApiResponse> {
            override fun onResponse(
                call: Call<InstructorApi.ApiResponse>,
                response: Response<InstructorApi.ApiResponse>
            ) {
                val instructorResponse = response.body()
                if (response.isSuccessful) {
                    instructorResponse?.let {
                        val listInstructors = mutableListOf<Instructor>()
                        for (instructor in it.data) {
                            listInstructors.add(instructor)
                        }
                        listener.onSuccess(listInstructors)
                    }
                    instructorResponse?.let { Log.i(Constant.TAG, it.message) }
                } else {
                    instructorResponse?.let { Log.i(Constant.TAG, it.message) }
                }
            }

            override fun onFailure(call: Call<InstructorApi.ApiResponse>, t: Throwable) {
                val ex = Exception("Oops.. Please try again")
                listener.onError(ex)
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