package com.example.swimminginstructorlocator.data.service.impl

import com.example.swimminginstructorlocator.data.model.Course
import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.data.model.InstructorDetail
import com.example.swimminginstructorlocator.listener.OnResultListener

interface InstructorServiceImpl {
    fun getInstructors(listener: OnResultListener<MutableList<Instructor>>)
    fun getInstructorDetail(id: String, listener: OnResultListener<InstructorDetail>)
    fun searchInstructor(searchValue: String, listener: OnResultListener<MutableList<Instructor>>)
    fun getListCourse(instructorId: String, listener: OnResultListener<MutableList<Course>>)
}