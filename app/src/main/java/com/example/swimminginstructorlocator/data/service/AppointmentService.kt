package com.example.swimminginstructorlocator.data.service

import com.example.swimminginstructorlocator.data.model.Appointment
import com.example.swimminginstructorlocator.data.repo.AppointmentRepo
import com.example.swimminginstructorlocator.data.service.impl.AppointmentServiceImpl
import com.example.swimminginstructorlocator.listener.OnResultListener
import com.example.swimminginstructorlocator.ui.appointment.list.AppointmentListContract

class AppointmentService(
    private val appointmentRepo: AppointmentRepo
): AppointmentServiceImpl {
    override fun getAppointmentByUserId(
        userId: String,
        listener: OnResultListener<MutableList<Appointment>>
    ) {
        appointmentRepo.getAppointmentByUserId(userId, listener)
    }

    override fun deleteAppointment(appointmentId: String, listener: OnResultListener<Boolean>) {
        appointmentRepo.deleteAppointment(appointmentId, listener)
    }

    companion object {
        private var instance: AppointmentService? = null

        fun getInstance(appointmentRepo: AppointmentRepo) = synchronized(this) {
            instance ?: AppointmentService(appointmentRepo).also {
                instance = it
            }
        }
    }
}