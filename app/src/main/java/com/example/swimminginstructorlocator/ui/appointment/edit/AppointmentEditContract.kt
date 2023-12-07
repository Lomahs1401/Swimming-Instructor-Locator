package com.example.swimminginstructorlocator.ui.appointment.edit

import com.example.swimminginstructorlocator.data.model.Appointment
import com.example.swimminginstructorlocator.utils.base.BasePresenter

class AppointmentEditContract {
    /**
     * View
     */
    interface View {
        fun onAppointmentEdit(appointment: Appointment)
        fun onError(exception: Exception?)
    }

    /**
     * Presenter
     */
    interface Presenter : BasePresenter<View> {
        fun appointmentEdit(appointment: Appointment)
    }
}