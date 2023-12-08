package com.example.swimminginstructorlocator.data.repo

import android.util.Log
import android.widget.Toast
import com.example.swimminginstructorlocator.api.AppointmentApi
import com.example.swimminginstructorlocator.constant.Constant
import com.example.swimminginstructorlocator.data.model.Appointment
import com.example.swimminginstructorlocator.data.request.CreateAppointmentRequest
import com.example.swimminginstructorlocator.listener.OnResultListener
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppointmentRepo {
    fun getAppointmentByUserId(userId: String, listener: OnResultListener<MutableList<Appointment>>) {
        val api = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AppointmentApi::class.java)
        val url = "appoiment/appointment-of-student/$userId"
        api.getAppointmentByUserId(url).enqueue(object : retrofit2.Callback<AppointmentApi.ApiResponse> {
            override fun onResponse(
                call: retrofit2.Call<AppointmentApi.ApiResponse>,
                response: retrofit2.Response<AppointmentApi.ApiResponse>
            ) {
                val appointmentResponse = response.body()
                if (response.isSuccessful) {
                    appointmentResponse?.let {
                        val listAppointment = mutableListOf<Appointment>()
                        for (appointment in it.data) {
                            listAppointment.add(appointment)
                        }
                        listener.onSuccess(listAppointment)
                    }
                    appointmentResponse?.let { println(it.message) }
                } else {
                    Log.d("Appointment", "Failed to get appointment")
                    appointmentResponse?.let { println(it.message) }
                }
            }

            override fun onFailure(call: retrofit2.Call<AppointmentApi.ApiResponse>, t: Throwable) {
                val ex = Exception("Oops.. Please try again")
                listener.onError(ex)
                println("onFailure: ${t.message}")
            }
        })
    }

    fun createAppointment(
        date: String,
        startTime: String,
        endTime: String,
        userId: String,
        instructorId: String,
        listener: OnResultListener<Appointment>
    ) {
        val api = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AppointmentApi::class.java)

        val request = CreateAppointmentRequest(
            instructorId = instructorId,
            studentId = userId,
            courseId = "6566e33de00b8b0ba4bbf0a4",  // Thay thế bằng ID thích hợp
            date = date,
            startTime = startTime,
            endTime = endTime,
            status = "done"  // Hoặc bạn có thể thay đổi giá trị status tùy ý
        )

        api.createAppointment(request).enqueue(object : retrofit2.Callback<AppointmentApi.CreateResponse> {
            override fun onResponse(
                call: retrofit2.Call<AppointmentApi.CreateResponse>,
                response: retrofit2.Response<AppointmentApi.CreateResponse>
            ) {
                val appointmentResponse = response.body()
                Log.d("Appointment", appointmentResponse.toString())
                if (response.isSuccessful) {
                    appointmentResponse?.let {
                        listener.onSuccess(appointmentResponse.data)
                    }
                    Log.i("CHECK_RESPONSE", appointmentResponse?.data.toString())
                    appointmentResponse?.let { println(it.message) }
                } else {
                    Log.d("Appointment", "Failed to get appointment")
                    appointmentResponse?.let { println(it.message) }
                }
            }

            override fun onFailure(call: retrofit2.Call<AppointmentApi.CreateResponse>, t: Throwable) {
                val ex = Exception("Oops.. Please try again")
                listener.onError(ex)
                println("onFailure: ${t.message}")
            }
        })
    }

    fun deleteAppointment(appointmentId: String, listener: OnResultListener<Boolean>) {
        val api = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AppointmentApi::class.java)
        val url = "appoiment/delete/$appointmentId"
        api.deleteAppointment(url).enqueue(object : retrofit2.Callback<AppointmentApi.ApiResponseDelete> {
            override fun onResponse(
                call: retrofit2.Call<AppointmentApi.ApiResponseDelete>,
                response: retrofit2.Response<AppointmentApi.ApiResponseDelete>
            ) {
                val appointmentResponse = response.body()
                if (response.isSuccessful) {
                    Log.d("Appointment", "Successfully delete appointment")
                    appointmentResponse?.let {
                        listener.onSuccess(true)
                    }
                    appointmentResponse?.let { println(it.message) }
                } else {
                    Log.d("Appointment", "Failed to delete appointment")
                    listener.onSuccess(false)
                    appointmentResponse?.let { println(it.message) }
                }
            }

            override fun onFailure(call: retrofit2.Call<AppointmentApi.ApiResponseDelete>, t: Throwable) {
                val ex = Exception("Oops.. Please try again")
                listener.onError(ex)
                println("onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private var instance: AppointmentRepo? = null

        fun getInstance(): AppointmentRepo {
            return synchronized(this) {
                instance ?: AppointmentRepo().also {
                    instance = it
                }
            }
        }
    }
}