package com.example.swimminginstructorlocator.listener

import com.example.swimminginstructorlocator.data.model.Instructor

interface OnInstructorItemClickListener {
    fun onInstructorImageClick(instructor: Instructor)
}