package com.example.swimminginstructorlocator.listener

import com.example.swimminginstructorlocator.data.model.Center
import com.example.swimminginstructorlocator.data.model.Instructor

interface OnItemClickListener {
    fun onCenterImageClick(center: Center)
    fun onInstructorImageClick(instructor: Instructor)
}