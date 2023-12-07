package com.example.swimminginstructorlocator.ui.appointment.edit

import com.example.swimminginstructorlocator.data.model.Appointment

class AppointmentEditPresenter(): AppointmentEditContract.Presenter{
    private var view: AppointmentEditContract.View? = null
    override fun appointmentEdit(appointment: Appointment) {

    }

    override fun onStart() {
    }

    override fun onStop() {
    }

    override fun setView(view: AppointmentEditContract.View?) {
        this.view = view
    }
}