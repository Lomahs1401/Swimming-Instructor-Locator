package com.example.swimminginstructorlocator.ui.viewMore.viewMoreCenter

import com.example.swimminginstructorlocator.data.model.Center
import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.utils.base.BasePresenter
import java.lang.Exception

class ViewMoreCenterContract {
    /**
     * View
     */
    interface View {
        fun onSearchCenter(listCenters: MutableList<Center>)
        fun onError(exception: Exception?)
    }

    /**
     * Presenter
     */
    interface Presenter : BasePresenter<View> {
        fun searchCenter(searchValue: String)
    }
}