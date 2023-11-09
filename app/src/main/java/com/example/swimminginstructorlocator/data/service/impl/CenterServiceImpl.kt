package com.example.swimminginstructorlocator.data.service.impl

import com.example.swimminginstructorlocator.data.model.Center
import com.example.swimminginstructorlocator.listener.OnResultListener

interface CenterServiceImpl {
    fun getCenters(listener: OnResultListener<MutableList<Center>>)
    fun searchCenters(searchValue: String, listener: OnResultListener<MutableList<Center>>)
}