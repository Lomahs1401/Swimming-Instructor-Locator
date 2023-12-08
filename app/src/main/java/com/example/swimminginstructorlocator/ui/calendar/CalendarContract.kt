package com.example.swimminginstructorlocator.ui.calendar

import com.example.swimminginstructorlocator.data.model.Appointment
import com.example.swimminginstructorlocator.data.model.Course
import com.example.swimminginstructorlocator.utils.base.BasePresenter
import java.lang.Exception

class CalendarContract {
    /**
     * View
     */
    interface View {
        fun onCreateCalendar(appointment: Appointment)
        fun onError(exception: Exception?)
    }

    /**
     * Presenter
     */
    interface Presenter : BasePresenter<View> {
        fun createCalendar(date: String, startTime: String, endTime: String)
    }
}