package com.example.swimminginstructorlocator.ui.viewMore.viewMoreCenter

import com.example.swimminginstructorlocator.data.model.Center
import com.example.swimminginstructorlocator.data.model.CenterDetail
import com.example.swimminginstructorlocator.data.service.impl.CenterServiceImpl
import com.example.swimminginstructorlocator.listener.OnResultListener
import java.lang.Exception

class ViewMoreCenterPresenter(
    private val centerService: CenterServiceImpl,
) : ViewMoreCenterContract.Presenter {

    private var view: ViewMoreCenterContract.View? = null

    override fun onStart() {
//        TODO("Not yet implemented")
    }

    override fun onStop() {
//        TODO("Not yet implemented")
    }

    override fun setView(view: ViewMoreCenterContract.View?) {
        this.view = view
    }

    override fun searchCenters(searchValue: String) {
        centerService.searchCenters(
            searchValue,
            object : OnResultListener<MutableList<Center>> {
                override fun onSuccess(dataResult: MutableList<Center>) {
                    view?.onSearchCenters(dataResult)
                }

                override fun onError(exception: Exception?) {
                    view?.onError(exception)
                }
            }
        )
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
}