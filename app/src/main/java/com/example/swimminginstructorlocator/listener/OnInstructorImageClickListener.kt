package com.example.swimminginstructorlocator.listener

import com.example.swimminginstructorlocator.data.model.Instructor

interface OnInstructorImageClickListener : OnImageClickListener {
    fun onInstructorImageClick(instructor: Instructor)
}