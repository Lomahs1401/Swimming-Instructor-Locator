package com.example.swimminginstructorlocator.ui.instructor.detail

import com.example.swimminginstructorlocator.data.model.InstructorDetail
import com.example.swimminginstructorlocator.data.service.impl.InstructorServiceImpl
import com.example.swimminginstructorlocator.listener.OnInstructorDetailListener
import com.example.swimminginstructorlocator.listener.OnResultListener
import java.lang.Exception

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
}