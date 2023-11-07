package com.example.swimminginstructorlocator.ui.center.detail

import com.example.swimminginstructorlocator.data.service.impl.CenterServiceImpl

class CenterDetailPresenter(
    private val centerService: CenterServiceImpl,
) : CenterDetailContract.Presenter {

    private var view: CenterDetailContract.View? = null

    override fun onStart() {
//        TODO("Not yet implemented")
    }

    override fun onStop() {
//        TODO("Not yet implemented")
    }

    override fun setView(view: CenterDetailContract.View?) {
        this.view = view
    }
}