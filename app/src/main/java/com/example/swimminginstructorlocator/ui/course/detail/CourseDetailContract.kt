package com.example.swimminginstructorlocator.ui.course.detail

import com.example.swimminginstructorlocator.utils.base.BasePresenter
import java.lang.Exception

class CourseDetailContract {
    /**
     * View
     */
    interface View {
        fun onError(exception: Exception?)
    }

    /**
     * Presenter
     */
    interface Presenter : BasePresenter<View> {
    }
}