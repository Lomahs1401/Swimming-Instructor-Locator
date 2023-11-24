package com.example.swimminginstructorlocator.ui.instructor.detail

import com.example.swimminginstructorlocator.data.model.InstructorDetail
import com.example.swimminginstructorlocator.listener.OnInstructorDetailListener
import com.example.swimminginstructorlocator.utils.base.BasePresenter
import java.lang.Exception

class InstructorDetailContract {
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