package com.example.swimminginstructorlocator.ui.profile

import com.example.swimminginstructorlocator.data.model.InstructorDetail
import com.example.swimminginstructorlocator.data.model.User
import com.example.swimminginstructorlocator.utils.base.BasePresenter
import java.lang.Exception

class ProfileContract {
    /**
     * View
     */
    interface View {
        fun onGetCurrentUser(user: User)
        fun onError(exception: Exception?)
    }

    /**
     * Presenter
     */
    interface Presenter : BasePresenter<View> {
        fun getCurrentUser()
    }
}