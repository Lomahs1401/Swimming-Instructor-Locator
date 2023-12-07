package com.example.swimminginstructorlocator.data.service.impl

import com.example.swimminginstructorlocator.data.model.Course
import com.example.swimminginstructorlocator.data.model.CourseDetail
import com.example.swimminginstructorlocator.listener.OnResultListener

interface CourseServiceImpl {
    fun getCourseDetail(id: String, listener: OnResultListener<CourseDetail>)
}