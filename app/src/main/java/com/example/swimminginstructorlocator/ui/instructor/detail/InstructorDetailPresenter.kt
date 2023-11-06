package com.example.swimminginstructorlocator.ui.instructor.detail

import com.example.swimminginstructorlocator.data.service.impl.InstructorServiceImpl

class InstructorDetailPresenter(
    private val instructorService: InstructorServiceImpl
) : InstructorDetailContract.Presenter {

    private var view: InstructorDetailContract.View? = null

    override fun onStart() {
//        TODO("Not yet implemented")
    }

    override fun onStop() {
//        TODO("Not yet implemented")
    }

    override fun setView(view: InstructorDetailContract.View?) {
        this.view = view
    }

    override fun searchInstructor() {
//        TODO("Not yet implemented")
    }
}