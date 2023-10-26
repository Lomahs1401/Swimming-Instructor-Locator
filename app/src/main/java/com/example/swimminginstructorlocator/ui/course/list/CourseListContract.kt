package com.example.swimminginstructorlocator.ui.course.list

import com.example.swimminginstructorlocator.utils.base.BasePresenter
import java.lang.Exception

class CourseListContract {
    /**
     * View
     */
    interface View {
        fun onSearchInstructor()
        fun onError(exception: Exception?)
    }

    /**
     * Presenter
     */
    interface Presenter : BasePresenter<View> {
        fun searchInstructor()
    }
}