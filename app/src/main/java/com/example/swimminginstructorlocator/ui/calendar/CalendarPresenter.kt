package com.example.swimminginstructorlocator.ui.calendar

import com.example.swimminginstructorlocator.data.model.Appointment
import com.example.swimminginstructorlocator.data.service.impl.AppointmentServiceImpl
import com.example.swimminginstructorlocator.listener.OnResultListener
import java.lang.Exception

class CalendarPresenter(
    private val appointmentService: AppointmentServiceImpl
) : CalendarContract.Presenter {

    private var view: CalendarContract.View? = null

    override fun onStart() {
//        TODO("Not yet implemented")
    }

    override fun onStop() {
//        TODO("Not yet implemented")
    }

    override fun setView(view: CalendarContract.View?) {
        this.view = view
    }

    override fun createCalendar(date: String, startTime: String, endTime: String) {
        appointmentService.createAppointment(
            date,
            startTime,
            endTime,
            "655dc17b853f2c4d569ddadb",
            "653db6adf5ba775de8721ceb",
            object : OnResultListener<Appointment> {
                override fun onSuccess(dataResult: Appointment) {
                    view?.onCreateCalendar(dataResult)
                }

                override fun onError(exception: Exception?) {
                    view?.onError(exception)
                }

            })
    }
}