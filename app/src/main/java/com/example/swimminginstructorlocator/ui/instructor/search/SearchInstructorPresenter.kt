package com.example.swimminginstructorlocator.ui.instructor.search

import com.example.swimminginstructorlocator.data.repo.InstructorRepo

class SearchInstructorPresenter(
    private val instructorRepo: InstructorRepo
) : SearchInstructorContract.Presenter {

    private var view: SearchInstructorContract.View? = null

    override fun onStart() {
//        TODO("Not yet implemented")
    }

    override fun onStop() {
//        TODO("Not yet implemented")
    }

    override fun setView(view: SearchInstructorContract.View?) {
        this.view = view
    }

    override fun searchInstructor() {
//        TODO("Not yet implemented")
    }
}