package com.example.swimminginstructorlocator.data.repo.source.fetchjson

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GetJsonFromUrl {
    private val BASE_URL = "https://api.example.com/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}