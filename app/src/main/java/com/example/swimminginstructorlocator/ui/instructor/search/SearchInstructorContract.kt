package com.example.swimminginstructorlocator.ui.instructor.search

import com.example.swimminginstructorlocator.utils.base.BasePresenter
import java.lang.Exception

class SearchInstructorContract {
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