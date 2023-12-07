package com.example.swimminginstructorlocator.ui.course.list

import com.example.swimminginstructorlocator.data.model.Course
import com.example.swimminginstructorlocator.data.model.CourseDetail
import com.example.swimminginstructorlocator.data.service.impl.CourseServiceImpl
import com.example.swimminginstructorlocator.listener.OnResultListener

class CourseListPresenter(
    private val courseService: CourseServiceImpl
) : CourseListContract.Presenter {

    private var view: CourseListContract.View? = null

    override fun onStart() {
//        TODO("Not yet implemented")
    }

    override fun onStop() {
//        TODO("Not yet implemented")
    }

    override fun setView(view: CourseListContract.View?) {
        this.view = view
    }

    override fun searchInstructor() {
//        TODO("Not yet implemented")
    }

    override fun getCourseDetail(id: String) {
        courseService.getCourseDetail(
            id,
            object : OnResultListener<CourseDetail> {
                override fun onSuccess(dataResult: CourseDetail) {
                    view?.onGetCourseDetail(dataResult)
                }

                override fun onError(exception: Exception?) {
                    view?.onError(exception)
                }
            }
        )
    }
}