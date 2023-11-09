package com.example.swimminginstructorlocator.data.service.impl

import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.listener.OnResultListener

interface InstructorServiceImpl {
    fun getInstructors(listener: OnResultListener<MutableList<Instructor>>)
    fun searchInstructor(searchValue: String, listener: OnResultListener<MutableList<Instructor>>)
}