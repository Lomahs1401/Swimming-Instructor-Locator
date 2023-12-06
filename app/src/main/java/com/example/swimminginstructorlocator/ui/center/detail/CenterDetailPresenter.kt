package com.example.swimminginstructorlocator.ui.center.detail

import com.example.swimminginstructorlocator.data.model.Course
import com.example.swimminginstructorlocator.data.service.impl.CenterServiceImpl
import com.example.swimminginstructorlocator.listener.OnResultListener
import java.lang.Exception

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

    override fun getCourseOfInstructor(instructorId: String) {
        centerService.getCourseOfInstructor(
            instructorId,
            object : OnResultListener<MutableList<Course>> {
                override fun onSuccess(dataResult: MutableList<Course>) {
                    view?.onGetCourseOfInstructor(dataResult)
                }

                override fun onError(exception: Exception?) {
                    view?.onError(exception)
                }
            }
        )
    }
}