package com.example.swimminginstructorlocator.ui.home

import com.example.swimminginstructorlocator.data.model.Center
import com.example.swimminginstructorlocator.data.model.Course
import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.utils.base.BasePresenter
import java.lang.Exception

class HomeContract {
    /**
     * View
     */
    interface View {
        fun onGetListCenters(listCenters: MutableList<Center>)
        fun onGetListInstructors(listInstructors: MutableList<Instructor>)
        fun onViewMoreCenters()
        fun onViewMoreInstructors()
        fun onError(exception: Exception?)
    }

    /**
     * Presenter
     */
    interface Presenter : BasePresenter<View> {
        fun getListCenters()
        fun getListInstructors()
        fun viewMoreCenters()
        fun viewMoreInstructors()
    }
}