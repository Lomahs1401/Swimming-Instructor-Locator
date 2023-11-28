package com.example.swimminginstructorlocator.data.repo

import com.example.swimminginstructorlocator.api.AuthApi
import com.example.swimminginstructorlocator.constant.Constant
import com.example.swimminginstructorlocator.data.model.User
import com.example.swimminginstructorlocator.data.request.LoginRequest
import com.example.swimminginstructorlocator.data.request.RegisterRequest
import com.example.swimminginstructorlocator.listener.OnResultListener
import com.google.gson.Gson
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

        api.login(loginRequest).enqueue(object : Callback<AuthApi.ApiSuccessResponse> {
            override fun onResponse(
                call: Call<AuthApi.ApiSuccessResponse>,
                response: Response<AuthApi.ApiSuccessResponse>
            ) {
                val userResponse = response.body()
                if (response.isSuccessful) {
                    userResponse?.let {
                        onResultListener.onSuccess(userResponse.user)
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    val gson = Gson()

                    try {
                        // Convert JSON format -> Class ApiErrorResponse
                        val apiErrorResponse = gson.fromJson(
                            errorBody,
                            AuthApi.ApiErrorResponse::class.java
                        )

                        // Get the value from the error field and pass it to onError
                        val ex = Exception(apiErrorResponse.error)
                        onResultListener.onError(ex)
                    } catch (e: Exception) {
                        val ex = Exception("Error: Unable to parse error message")
                        onResultListener.onError(ex)
                    }
                }
            }

            override fun onFailure(call: Call<AuthApi.ApiSuccessResponse>, t: Throwable) {
                val ex = Exception("Oops.. Please try again")
                onResultListener.onError(ex)
            }
        })
    }

    fun register(registerRequest: RegisterRequest, onResultListener: OnResultListener<User>) {
        val api = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)

        api.register(registerRequest).enqueue(object : Callback<AuthApi.ApiSuccessResponse> {
            override fun onResponse(
                call: Call<AuthApi.ApiSuccessResponse>,
                response: Response<AuthApi.ApiSuccessResponse>
            ) {
                if (response.isSuccessful) {
                    val apiSuccessResponse: AuthApi.ApiSuccessResponse? = response.body()
                    if (apiSuccessResponse != null) {
                        onResultListener.onSuccess(apiSuccessResponse.user)
                    } else {
                        onResultListener.onError(Exception("Null response body"))
                    }
                } else {
                    val errorMessage = response.errorBody()?.string()
                    onResultListener.onError(Exception("Error: $errorMessage"))
                }
            }

            override fun onFailure(call: Call<AuthApi.ApiSuccessResponse>, t: Throwable) {
                val ex = Exception("Oops.. Please try again")
                onResultListener.onError(ex)
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