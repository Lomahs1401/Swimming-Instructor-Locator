package com.example.swimminginstructorlocator.ui.instructor.detail

import com.example.swimminginstructorlocator.data.model.Course
import com.example.swimminginstructorlocator.data.model.CourseDetail
import com.example.swimminginstructorlocator.data.model.InstructorDetail
import com.example.swimminginstructorlocator.listener.OnInstructorDetailListener
import com.example.swimminginstructorlocator.utils.base.BasePresenter
import java.lang.Exception

class InstructorDetailContract {
    /**
     * View
     */
    interface View {
        fun onGetListCourse(listCourse: MutableList<Course>)
        fun onGetCourseDetail(course: CourseDetail)
        fun onError(exception: Exception?)
    }

    /**
     * Presenter
     */
    interface Presenter : BasePresenter<View> {
        fun getListCourse(instructorId: String)
        fun getCourseDetail(id: String)
    }
}