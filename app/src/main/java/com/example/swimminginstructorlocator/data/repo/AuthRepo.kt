package com.example.swimminginstructorlocator.data.repo

import android.util.Log
import com.example.swimminginstructorlocator.api.AuthApi
import com.example.swimminginstructorlocator.constant.Constant
import com.example.swimminginstructorlocator.data.model.User
import com.example.swimminginstructorlocator.data.request.LoginRequest
import com.example.swimminginstructorlocator.data.request.RegisterRequest
import com.example.swimminginstructorlocator.listener.OnResultListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthRepo {
    fun login(loginRequest: LoginRequest, onResultListener: OnResultListener<User>) {
        val api = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)

        api.login(loginRequest).enqueue(object : Callback<AuthApi.ApiResponse> {
            override fun onResponse(
                call: Call<AuthApi.ApiResponse>,
                response: Response<AuthApi.ApiResponse>
            ) {
                if (response.isSuccessful) {
                    val apiResponse: AuthApi.ApiResponse? = response.body()
                    if (apiResponse != null) {
                        onResultListener.onSuccess(apiResponse.user)
                    } else {
                        onResultListener.onError(Exception("Null response body"))
                    }
                } else {
                    val errorMessage = response.errorBody()?.string()
                    onResultListener.onError(Exception("Error: $errorMessage"))
                }
            }

            override fun onFailure(call: Call<AuthApi.ApiResponse>, t: Throwable) {
                val ex = Exception("Oops.. Please try again")
                onResultListener.onError(ex)
                Log.e(Constant.TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun register(registerRequest: RegisterRequest, onResultListener: OnResultListener<User>) {
        val api = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)

        api.register(registerRequest).enqueue(object : Callback<AuthApi.ApiResponse> {
            override fun onResponse(
                call: Call<AuthApi.ApiResponse>,
                response: Response<AuthApi.ApiResponse>
            ) {
                if (response.isSuccessful) {
                    val apiResponse: AuthApi.ApiResponse? = response.body()
                    if (apiResponse != null) {
                        onResultListener.onSuccess(apiResponse.user)
                    } else {
                        onResultListener.onError(Exception("Null response body"))
                    }
                } else {
                    val errorMessage = response.errorBody()?.string()
                    onResultListener.onError(Exception("Error: $errorMessage"))
                }
            }

            override fun onFailure(call: Call<AuthApi.ApiResponse>, t: Throwable) {
                val ex = Exception("Oops.. Please try again")
                onResultListener.onError(ex)
                Log.e(Constant.TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object{
        private var instance: AuthRepo? = null
        fun getInstance() = synchronized(this) {
            instance ?: AuthRepo().also {
                instance = it
            }
        }
    }
}