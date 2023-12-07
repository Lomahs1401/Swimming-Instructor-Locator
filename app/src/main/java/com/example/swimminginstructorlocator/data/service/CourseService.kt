package com.example.swimminginstructorlocator.data.service

import com.example.swimminginstructorlocator.data.model.Course
import com.example.swimminginstructorlocator.data.model.CourseDetail
import com.example.swimminginstructorlocator.data.repo.CourseRepo
import com.example.swimminginstructorlocator.data.service.impl.CourseServiceImpl
import com.example.swimminginstructorlocator.listener.OnResultListener

class CourseService (
    private val courseRepo: CourseRepo
): CourseServiceImpl {
    override fun getCourseDetail(id: String, listener: OnResultListener<CourseDetail>) {
        courseRepo.getCourseDetail(id, listener)
    }

    companion object {
        private var instance: CourseService? = null
        fun getInstance(courseRepo: CourseRepo) = synchronized(this) {
            instance ?: CourseService(courseRepo).also {
                instance = it
            }
        }
    }
}