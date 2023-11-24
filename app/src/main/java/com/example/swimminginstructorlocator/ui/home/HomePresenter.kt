package com.example.swimminginstructorlocator.ui.home

import com.example.swimminginstructorlocator.data.model.Center
import com.example.swimminginstructorlocator.data.model.CenterDetail
import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.data.model.InstructorDetail
import com.example.swimminginstructorlocator.data.service.CenterService
import com.example.swimminginstructorlocator.data.service.impl.InstructorServiceImpl
import com.example.swimminginstructorlocator.listener.OnResultListener
import java.lang.Exception

class HomePresenter(
    private val instructorService: InstructorServiceImpl,
    private val centerService: CenterService
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
        centerService.getCenters(object : OnResultListener<MutableList<Center>> {
            override fun onSuccess(dataResult: MutableList<Center>) {
                view?.onGetListCenters(dataResult)
            }

            override fun onError(exception: Exception?) {
                view?.onError(exception)
            }
        })
    }

    override fun getListInstructors() {
        instructorService.getInstructors(object : OnResultListener<MutableList<Instructor>> {
            override fun onSuccess(dataResult: MutableList<Instructor>) {
                view?.onGetListInstructors(dataResult)
            }

            override fun onError(exception: Exception?) {
                view?.onError(exception)
            }
        })
    }

    override fun getCenterDetail(id: String) {
        centerService.getCenterDetail(
            id,
            object : OnResultListener<CenterDetail> {
                override fun onSuccess(dataResult: CenterDetail) {
                    view?.onGetCenterDetail(dataResult)
                }

                override fun onError(exception: Exception?) {
                    view?.onError(exception)
                }
            }
        )
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

    override fun viewMoreCenters() {
        view?.onViewMoreCenters()
    }


    override fun viewMoreInstructors() {
        view?.onViewMoreInstructors()
    }
}