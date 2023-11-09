package com.example.swimminginstructorlocator.data.repo

import android.util.Log
import com.example.swimminginstructorlocator.api.CenterApi
import com.example.swimminginstructorlocator.constant.Constant
import com.example.swimminginstructorlocator.data.model.Center
import com.example.swimminginstructorlocator.listener.OnResultListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
                call: Call<CenterApi.ApiResponse>,
                response: Response<CenterApi.ApiResponse>
            ) {
                val centerResponse = response.body()
                if (response.isSuccessful) {
                    centerResponse?.let {
                        val listCenters = mutableListOf<Center>()
                        for (center in it.data) {
                            listCenters.add(center)
                        }
                        listener.onSuccess(listCenters)
                    }
                    centerResponse?.let { Log.i(Constant.TAG, it.message) }
                } else {
                    centerResponse?.let { Log.i(Constant.TAG, it.message) }
                }
            }

            override fun onFailure(call: Call<CenterApi.ApiResponse>, t: Throwable) {
                val ex = Exception("Oops.. Please try again")
                listener.onError(ex)
                Log.e(Constant.TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun searchCenters(searchValue: String, listener: OnResultListener<MutableList<Center>>) {
        val api = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CenterApi::class.java)

        // Xây dựng URL đầy đủ với path và query parameter
        val url = "search/center/q=$searchValue"

        if (searchValue.isEmpty()) {
            api.getCenters().enqueue(object : Callback<CenterApi.ApiResponse> {
                override fun onResponse(
                    call: Call<CenterApi.ApiResponse>,
                    response: Response<CenterApi.ApiResponse>
                ) {
                    val centerResponse = response.body()
                    if (response.isSuccessful) {
                        centerResponse?.let {
                            val listCenters = mutableListOf<Center>()
                            for (center in it.data) {
                                listCenters.add(center)
                            }
                            listener.onSuccess(listCenters)
                        }
                        centerResponse?.let { Log.i(Constant.TAG, it.message) }
                    } else {
                        centerResponse?.let { Log.i(Constant.TAG, it.message) }
                    }
                }

                override fun onFailure(call: Call<CenterApi.ApiResponse>, t: Throwable) {
                    val ex = Exception("Oops.. Please try again")
                    listener.onError(ex)
                    Log.e(Constant.TAG, "onFailure: ${t.message}")
                }
            })
        } else {
            api.searchCenters(url).enqueue(object : Callback<CenterApi.SearchApiResponse> {
                override fun onResponse(
                    call: Call<CenterApi.SearchApiResponse>,
                    response: Response<CenterApi.SearchApiResponse>
                ) {
                    Log.i(Constant.TAG, "LMAOMOMWEQWE")
                    val centerResponse = response.body()
                    if (response.isSuccessful) {
                        centerResponse?.let {
                            val listCenters = it.data.centers.toMutableList()
                            listener.onSuccess(listCenters)
                        }
                        centerResponse?.let { Log.i(Constant.TAG, it.message) }
                    } else {
                        centerResponse?.let { Log.i(Constant.TAG, it.message) }
                    }
                }

                override fun onFailure(call: Call<CenterApi.SearchApiResponse>, t: Throwable) {
                    val ex = Exception("Oops.. Please try again")
                    listener.onError(ex)
                    Log.e(Constant.TAG, "onFailure: ${t.message}")
                }
            })
        }
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