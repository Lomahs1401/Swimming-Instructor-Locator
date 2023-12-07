package com.example.swimminginstructorlocator.data.repo

import com.example.swimminginstructorlocator.api.CourseApi
import com.example.swimminginstructorlocator.constant.Constant
import com.example.swimminginstructorlocator.data.model.CourseDetail
import com.example.swimminginstructorlocator.listener.OnResultListener
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CourseRepo {
    fun getCourseDetail(id: String, listener: OnResultListener<CourseDetail>) {
        val api = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CourseApi::class.java)

        val url = "course/$id"

        api.getCourseDetail(url).enqueue(object : retrofit2.Callback<CourseApi.ApiDetailResponse> {
            override fun onResponse(
                call: retrofit2.Call<CourseApi.ApiDetailResponse>,
                response: retrofit2.Response<CourseApi.ApiDetailResponse>
            ) {
                val courseResponse = response.body()
                if (response.isSuccessful) {
                    courseResponse?.let {
                        val course = it.data
                        listener.onSuccess(course)
                    }
                } else {
                    courseResponse?.let { listener.onError(Exception(it.message)) }
                }
            }

            override fun onFailure(call: retrofit2.Call<CourseApi.ApiDetailResponse>, t: Throwable) {
                listener.onError(Exception("Oops.. Please try again"))
            }
        })
    }

    companion object {
        private var instance: CourseRepo? = null

        fun getInstance(): CourseRepo {
            if (instance == null) {
                instance = CourseRepo()
            }
            return instance!!
        }
    }
}