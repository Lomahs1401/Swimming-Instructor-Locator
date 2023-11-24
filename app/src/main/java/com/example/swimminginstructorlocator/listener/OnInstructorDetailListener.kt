package com.example.swimminginstructorlocator.listener

import com.example.swimminginstructorlocator.data.model.InstructorDetail

interface OnInstructorDetailListener {
    fun onInstructorDetailSuccess(instructorDetail: InstructorDetail)
    fun onInstructorDetailError(exception: Exception?)
}