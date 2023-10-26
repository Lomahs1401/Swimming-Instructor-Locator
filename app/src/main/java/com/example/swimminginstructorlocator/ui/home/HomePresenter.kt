package com.example.swimminginstructorlocator.ui.home

import com.example.swimminginstructorlocator.data.repo.InstructorRepo

class HomePresenter(
    private val instructorRepo: InstructorRepo
) : HomeContract.Presenter {

    private var view: HomeContract.View? = null

    override fun onStart() {
//        TODO("Not yet implemented")
    }

    override fun onStop() {
//        TODO("Not yet implemented")
    }

    override fun setView(view: HomeContract.View?) {
        this.view = view
    }

    override fun getListInstructors() {
//        TODO("Not yet implemented")
    }

    override fun searchInstructor() {
//        TODO("Not yet implemented")
    }

    override fun viewMoreInstructors() {
//        TODO("Not yet implemented")
    }
}