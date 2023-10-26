package com.example.swimminginstructorlocator.ui.home

import com.example.swimminginstructorlocator.utils.base.BasePresenter
import java.lang.Exception

class HomeContract {
    /**
     * View
     */
    interface View {
        fun onGetListInstructor()
        fun onSearchInstructor()
        fun onViewMoreInstructors()
        fun onError(exception: Exception?)
    }

    /**
     * Presenter
     */
    interface Presenter : BasePresenter<View> {
        fun getListInstructors()
        fun searchInstructor()
        fun viewMoreInstructors()
    }
}