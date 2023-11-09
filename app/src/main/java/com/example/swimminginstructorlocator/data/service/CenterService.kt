package com.example.swimminginstructorlocator.data.service

import com.example.swimminginstructorlocator.data.model.Center
import com.example.swimminginstructorlocator.data.repo.CenterRepo
import com.example.swimminginstructorlocator.data.service.impl.CenterServiceImpl
import com.example.swimminginstructorlocator.listener.OnResultListener

class CenterService(
    private val centerRepo: CenterRepo
) : CenterServiceImpl {
    override fun getCenters(listener: OnResultListener<MutableList<Center>>) {
        centerRepo.getCenters(listener)
    }

    override fun searchCenters(searchValue: String, listener: OnResultListener<MutableList<Center>>) {
        centerRepo.searchCenters(searchValue, listener)
    }

    companion object {
        private var instance: CenterService? = null

        fun getInstance(centerRepo: CenterRepo) = synchronized(this) {
            instance ?: CenterService(centerRepo).also {
                instance = it
            }
        }
    }
}