package com.example.swimminginstructorlocator.ui.instructor.detail

import com.example.swimminginstructorlocator.data.model.Course
import com.example.swimminginstructorlocator.data.model.CourseDetail
import com.example.swimminginstructorlocator.data.service.impl.CourseServiceImpl
import com.example.swimminginstructorlocator.data.service.impl.InstructorServiceImpl
import com.example.swimminginstructorlocator.listener.OnResultListener
import java.lang.Exception

class InstructorDetailPresenter(
    private val instructorService: InstructorServiceImpl,
    private val courseService: CourseServiceImpl
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

    override fun getListCourse(instructorId: String) {
        instructorService.getListCourse(
            instructorId,
            object : OnResultListener<MutableList<Course>> {
                override fun onSuccess(dataResult: MutableList<Course>) {
                    view?.onGetListCourse(dataResult)
                }

                override fun onError(exception: Exception?) {
                    view?.onError(exception)
                }
            }
        )
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