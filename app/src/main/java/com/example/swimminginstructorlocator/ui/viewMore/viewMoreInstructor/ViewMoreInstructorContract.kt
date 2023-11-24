package com.example.swimminginstructorlocator.ui.viewMore.viewMoreInstructor

import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.data.model.InstructorDetail
import com.example.swimminginstructorlocator.utils.base.BasePresenter
import java.lang.Exception

class ViewMoreInstructorContract {
    /**
     * View
     */
    interface View {
        fun onSearchInstructors(listInstructors: MutableList<Instructor>)
        fun onGetInstructorDetail(instructorDetail: InstructorDetail)
        fun onError(exception: Exception?)
    }

    /**
     * Presenter
     */
    interface Presenter : BasePresenter<View> {
        fun searchInstructor(searchValue: String)
        fun getInstructorDetail(id: String)
    }
}