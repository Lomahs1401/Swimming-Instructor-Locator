package com.example.swimminginstructorlocator.ui.viewMore.viewMoreInstructor

import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.data.model.InstructorDetail
import com.example.swimminginstructorlocator.data.service.impl.InstructorServiceImpl
import com.example.swimminginstructorlocator.listener.OnResultListener
import java.lang.Exception

class ViewMoreInstructorPresenter(
    private val instructorService: InstructorServiceImpl,
) : ViewMoreInstructorContract.Presenter {

    private var view: ViewMoreInstructorContract.View? = null

    override fun onStart() {
//        TODO("Not yet implemented")
    }

    override fun onStop() {
//        TODO("Not yet implemented")
    }

    override fun setView(view: ViewMoreInstructorContract.View?) {
        this.view = view
    }

    override fun searchInstructor(searchValue: String) {
        instructorService.searchInstructor(
            searchValue,
            object : OnResultListener<MutableList<Instructor>> {
                override fun onSuccess(dataResult: MutableList<Instructor>) {
                    view?.onSearchInstructors(dataResult)
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
}