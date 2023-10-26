package com.example.swimminginstructorlocator.ui.notifications

import com.example.swimminginstructorlocator.utils.base.BasePresenter
import java.lang.Exception

class NotificationsContract {
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