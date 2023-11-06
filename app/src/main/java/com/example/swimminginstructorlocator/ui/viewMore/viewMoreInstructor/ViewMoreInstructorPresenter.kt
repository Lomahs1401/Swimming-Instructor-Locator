package com.example.swimminginstructorlocator.ui.viewMore.viewMoreInstructor

import com.example.swimminginstructorlocator.data.service.impl.InstructorServiceImpl

class ViewMoreInstructorPresenter(
    private val instructorService: InstructorServiceImpl,
) : ViewMoreInstructorContract.Presenter {

    private var view: ViewMoreInstructorContract.View? = null

    override fun onStart() {
//        TODO("Not yet implemented")
    }

    override fun onStop() {
//        TODO("Not yet implemented")
    }

    override fun setView(view: ViewMoreInstructorContract.View?) {
        this.view = view
    }

    override fun searchInstructor(searchValue: String) {
//        TODO("Not yet implemented")
    }
}