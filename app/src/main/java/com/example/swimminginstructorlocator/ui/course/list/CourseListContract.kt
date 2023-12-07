package com.example.swimminginstructorlocator.ui.course.list

import com.example.swimminginstructorlocator.data.model.Course
import com.example.swimminginstructorlocator.data.model.CourseDetail
import com.example.swimminginstructorlocator.utils.base.BasePresenter
import java.lang.Exception

class CourseListContract {
    /**
     * View
     */
    interface View {
        fun onSearchInstructor()
        fun onGetCourseDetail(course: CourseDetail)
        fun onError(exception: Exception?)
    }

    /**
     * Presenter
     */
    interface Presenter : BasePresenter<View> {
        fun searchInstructor()
        fun getCourseDetail(id: String)
    }
}