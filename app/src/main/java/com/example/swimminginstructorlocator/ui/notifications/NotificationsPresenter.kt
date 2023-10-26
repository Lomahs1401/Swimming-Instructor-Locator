package com.example.swimminginstructorlocator.ui.notifications

import com.example.swimminginstructorlocator.ui.dashboard.DashboardContract

class NotificationsPresenter : NotificationsContract.Presenter {

    private var view: NotificationsContract.View? = null
    override fun onStart() {
//        TODO("Not yet implemented")
    }

    override fun onStop() {
//        TODO("Not yet implemented")
    }

    override fun setView(view: NotificationsContract.View?) {
        this.view = view
    }
}