package com.example.swimminginstructorlocator.ui.appointment.list

import com.example.swimminginstructorlocator.data.model.Appointment
import com.example.swimminginstructorlocator.utils.base.BasePresenter

class AppointmentListContract {

    interface View {
        fun onAppointmentList(listAppointment: MutableList<Appointment>)
        fun onDeletedAppointment(boolean: Boolean)
        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun appointmentList()
        fun deleteAppointment(appointmentId: String)
    }
}