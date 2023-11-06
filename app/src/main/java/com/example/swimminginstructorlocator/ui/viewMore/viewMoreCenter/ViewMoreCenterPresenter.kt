package com.example.swimminginstructorlocator.ui.viewMore.viewMoreCenter

import com.example.swimminginstructorlocator.data.service.impl.CenterServiceImpl

class ViewMoreCenterPresenter(
    private val centerService: CenterServiceImpl,
) : ViewMoreCenterContract.Presenter {

    private var view: ViewMoreCenterContract.View? = null

    override fun onStart() {
//        TODO("Not yet implemented")
    }

    override fun onStop() {
//        TODO("Not yet implemented")
    }

    override fun setView(view: ViewMoreCenterContract.View?) {
        this.view = view
    }

    override fun searchCenter(searchValue: String) {
//        TODO("Not yet implemented")
    }
}