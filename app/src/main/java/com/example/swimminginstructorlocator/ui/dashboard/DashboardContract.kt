package com.example.swimminginstructorlocator.ui.dashboard

import com.example.swimminginstructorlocator.utils.base.BasePresenter
import java.lang.Exception

class DashboardContract {
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