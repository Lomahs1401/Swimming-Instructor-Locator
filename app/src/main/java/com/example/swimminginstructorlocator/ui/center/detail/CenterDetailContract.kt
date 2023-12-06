package com.example.swimminginstructorlocator.ui.center.detail

import com.example.swimminginstructorlocator.data.model.Course
import com.example.swimminginstructorlocator.utils.base.BasePresenter
import java.lang.Exception

class CenterDetailContract {
    /**
     * View
     */
    interface View {
        fun onGetCourseOfInstructor(listCourse: MutableList<Course>)
        fun onError(exception: Exception?)
    }

    /**
     * Presenter
     */
    interface Presenter : BasePresenter<View> {
        fun getCourseOfInstructor(instructorId: String)
    }
}