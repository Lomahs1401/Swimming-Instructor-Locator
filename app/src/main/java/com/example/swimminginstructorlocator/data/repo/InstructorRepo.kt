package com.example.swimminginstructorlocator.data.repo

import android.util.Log
import com.example.swimminginstructorlocator.api.CenterApi
import com.example.swimminginstructorlocator.api.InstructorApi
import com.example.swimminginstructorlocator.constant.Constant
import com.example.swimminginstructorlocator.data.model.Center
import com.example.swimminginstructorlocator.data.model.Course
import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.data.model.InstructorDetail
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

    fun searchInstructors(searchValue: String, listener: OnResultListener<MutableList<Instructor>>) {
        if (searchValue.isEmpty()) {
            getInstructors(listener)
        } else {
            val api = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(InstructorApi::class.java)

            // Xây dựng URL đầy đủ với path và query parameter
            val url = "search/teacher/q=$searchValue"

            api.searchInstructors(url).enqueue(object : Callback<InstructorApi.SearchApiResponse> {
                override fun onResponse(
                    call: Call<InstructorApi.SearchApiResponse>,
                    response: Response<InstructorApi.SearchApiResponse>
                ) {
                    val instructorResponse = response.body()
                    if (response.isSuccessful) {
                        instructorResponse?.let {
                            val listInstructors = it.data.teachers.toMutableList()
                            listener.onSuccess(listInstructors)
                        }
                        instructorResponse?.let { Log.i(Constant.TAG, it.message) }
                    } else {
                        instructorResponse?.let { Log.i(Constant.TAG, it.message) }
                    }
                }

                override fun onFailure(call: Call<InstructorApi.SearchApiResponse>, t: Throwable) {
                    val ex = Exception("Oops.. Please try again")
                    listener.onError(ex)
                    Log.e(Constant.TAG, "onFailure: ${t.message}")
                }
            })
        }
    }

    fun getInstructorDetail(id: String, listener: OnResultListener<InstructorDetail>) {
        val api = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(InstructorApi::class.java)

        // Xây dựng URL đầy đủ với path và query parameter
        val url = "teacher/full/${id}"

        api.getInstructorDetail(url).enqueue(object : Callback<InstructorApi.InstructorDetailApiResponse> {
            override fun onResponse(
                call: Call<InstructorApi.InstructorDetailApiResponse>,
                response: Response<InstructorApi.InstructorDetailApiResponse>
            ) {
                val instructorResponse = response.body()
                if (response.isSuccessful) {
                    instructorResponse?.let {
                        val instructorDetail = it.data
                        listener.onSuccess(instructorDetail)
                    }
                    instructorResponse?.let { Log.i(Constant.TAG, it.message) }
                } else {
                    instructorResponse?.let { Log.i(Constant.TAG, it.message) }
                }
            }

            override fun onFailure(call: Call<InstructorApi.InstructorDetailApiResponse>, t: Throwable) {
                val ex = Exception("Oops.. Please try again")
                listener.onError(ex)
                Log.e(Constant.TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getListCourse(instructorId: String, listener: OnResultListener<MutableList<Course>>) {
        val api = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(InstructorApi::class.java)

        // Xây dựng URL đầy đủ với path và query parameter
        val url = "course/course-of-teacher/${instructorId}"

        api.getListCourse(url).enqueue(object : Callback<InstructorApi.ListCourseResponse> {
            override fun onResponse(
                call: Call<InstructorApi.ListCourseResponse>,
                response: Response<InstructorApi.ListCourseResponse>
            ) {
                val courseResponse = response.body()
                if (response.isSuccessful) {
                    courseResponse?.let {
                        val listCourse = it.data
                        listener.onSuccess(listCourse)
                    }
                    courseResponse?.let { Log.i(Constant.TAG, it.message) }
                } else {
                    courseResponse?.let { Log.i(Constant.TAG, it.message) }
                }
            }

            override fun onFailure(call: Call<InstructorApi.ListCourseResponse>, t: Throwable) {
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