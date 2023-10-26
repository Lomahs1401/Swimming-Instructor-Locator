package com.example.swimminginstructorlocator.ui.instructor.detail

import com.example.swimminginstructorlocator.utils.base.BasePresenter
import java.lang.Exception

class InstructorDetailContract {
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