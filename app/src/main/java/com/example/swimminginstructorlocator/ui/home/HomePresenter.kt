package com.example.swimminginstructorlocator.ui.home

import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.data.service.impl.InstructorServiceImpl
import com.example.swimminginstructorlocator.data.service.local.InstructorServiceLocal
import com.example.swimminginstructorlocator.data.service.remote.InstructorServiceRemote
import com.example.swimminginstructorlocator.listener.OnResultListener
import java.lang.Exception

class HomePresenter(
    private val instructorServiceLocal: InstructorServiceLocal?,
    private val instructorServiceRemote: InstructorServiceRemote?,
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

    override fun getListCenters() {
//        TODO("Not yet implemented")
    }

    override fun getListCourses() {
//        TODO("Not yet implemented")
    }

    override fun getListInstructors() {
        instructorServiceRemote?.getInstructors(object : OnResultListener<MutableList<Instructor>> {
            override fun onSuccess(dataResult: MutableList<Instructor>) {
//                TODO("Not yet implemented")
            }

            override fun onError(exception: Exception?) {
//                TODO("Not yet implemented")
            }
        })
    }

    override fun viewMoreCenters() {
//        TODO("Not yet implemented")
    }

    override fun viewMoreCourses() {
//        TODO("Not yet implemented")
    }

    override fun viewMoreInstructors() {
//        TODO("Not yet implemented")
    }
}