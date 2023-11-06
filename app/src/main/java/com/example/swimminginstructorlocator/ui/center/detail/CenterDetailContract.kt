package com.example.swimminginstructorlocator.ui.center.detail

import com.example.swimminginstructorlocator.utils.base.BasePresenter
import java.lang.Exception

class CenterDetailContract {
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