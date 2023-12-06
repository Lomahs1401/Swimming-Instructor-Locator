package com.example.swimminginstructorlocator.data.service.impl

import com.example.swimminginstructorlocator.data.model.Appointment
import com.example.swimminginstructorlocator.listener.OnResultListener
import com.example.swimminginstructorlocator.ui.appointment.list.AppointmentListContract

interface AppointmentServiceImpl {
    fun getAppointmentByUserId(userId: String, listener: OnResultListener<MutableList<Appointment>>)
    fun deleteAppointment(appointmentId: String, listener: OnResultListener<Boolean>)
}