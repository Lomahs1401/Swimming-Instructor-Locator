package com.example.swimminginstructorlocator.data.repo

import com.example.swimminginstructorlocator.api.CenterApi
import com.example.swimminginstructorlocator.constant.Constant
import com.example.swimminginstructorlocator.data.model.Center
import com.example.swimminginstructorlocator.listener.OnResultListener
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CenterRepo {
    fun getCenters(listener: OnResultListener<MutableList<Center>>) {
        val api = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CenterApi::class.java)

        api.getCenters().enqueue(object : Callback<CenterApi.ApiResponse> {
            override fun onResponse(
                call: retrofit2.Call<CenterApi.ApiResponse>,
                response: retrofit2.Response<CenterApi.ApiResponse>
            ) {
                val centerResponse = response.body()
                if (response.isSuccessful) {
                    centerResponse?.let {
                        val listCenters = mutableListOf<Center>()
                        listener.onSuccess(listCenters)
                    }
                    centerResponse?.let { android.util.Log.i(Constant.TAG, it.message) }
                } else {
                    centerResponse?.let { android.util.Log.i(Constant.TAG, it.message) }
                }
            }

            override fun onFailure(call: retrofit2.Call<CenterApi.ApiResponse>, t: Throwable) {
                val ex = Exception("Oops.. Please try again")
                listener.onError(ex)
                android.util.Log.e(Constant.TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private var instance: CenterRepo? = null

        fun getInstance(): CenterRepo {
            return synchronized(this) {
                instance ?: CenterRepo().also {
                    instance = it
                }
            }
        }
    }
}