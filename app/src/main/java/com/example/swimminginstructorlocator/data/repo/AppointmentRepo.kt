package com.example.swimminginstructorlocator.data.repo

import android.util.Log
import android.widget.Toast
import com.example.swimminginstructorlocator.api.AppointmentApi
import com.example.swimminginstructorlocator.constant.Constant
import com.example.swimminginstructorlocator.data.model.Appointment
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

    fun deleteAppointment(appointmentId: String, listener: OnResultListener<Boolean>) {
        val api = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AppointmentApi::class.java)
        val url = "appoiment/$appointmentId"
        api.deleteAppointment(url).enqueue(object : retrofit2.Callback<AppointmentApi.ApiResponse> {
            override fun onResponse(
                call: retrofit2.Call<AppointmentApi.ApiResponse>,
                response: retrofit2.Response<AppointmentApi.ApiResponse>
            ) {
                val appointmentResponse = response.body()
                if (response.isSuccessful) {
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

            override fun onFailure(call: retrofit2.Call<AppointmentApi.ApiResponse>, t: Throwable) {
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