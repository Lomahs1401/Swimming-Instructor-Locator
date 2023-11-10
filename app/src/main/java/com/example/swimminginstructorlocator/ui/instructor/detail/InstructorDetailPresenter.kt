package com.example.swimminginstructorlocator.ui.instructor.detail

import com.example.swimminginstructorlocator.data.model.InstructorDetail
import com.example.swimminginstructorlocator.data.service.impl.InstructorServiceImpl
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

    override fun getInstructorDetail(id: String) {
        instructorService.getInstructorDetail(
            id,
            object : OnResultListener<InstructorDetail> {
                override fun onSuccess(dataResult: InstructorDetail) {
                    view?.onGetInstructorDetail(dataResult)
                }

                override fun onError(exception: Exception?) {
                    view?.onError(exception)
                }
            }
        )
    }
}