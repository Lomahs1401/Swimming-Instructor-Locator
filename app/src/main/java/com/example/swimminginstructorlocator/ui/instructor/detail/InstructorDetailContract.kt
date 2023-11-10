package com.example.swimminginstructorlocator.ui.instructor.detail

import com.example.swimminginstructorlocator.data.model.InstructorDetail
import com.example.swimminginstructorlocator.utils.base.BasePresenter
import java.lang.Exception

class InstructorDetailContract {
    /**
     * View
     */
    interface View {
        fun onGetInstructorDetail(instructorDetail: InstructorDetail)
        fun onError(exception: Exception?)
    }

    /**
     * Presenter
     */
    interface Presenter : BasePresenter<View> {
        fun getInstructorDetail(id: String)
    }
}