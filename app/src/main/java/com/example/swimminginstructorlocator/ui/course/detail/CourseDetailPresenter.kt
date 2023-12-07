package com.example.swimminginstructorlocator.ui.course.detail

class CourseDetailPresenter (

): CourseDetailContract.Presenter{
    private var view: CourseDetailContract.View? = null
    override fun onStart() {
        TODO("Not yet implemented")
    }

    override fun onStop() {
        TODO("Not yet implemented")
    }

    override fun setView(view: CourseDetailContract.View?) {
        this.view = view
    }
}