package com.example.swimminginstructorlocator.listener

import com.example.swimminginstructorlocator.data.model.Appointment

interface OnAppointmentItemClickListener {
    fun onAppointmentItemEditClick(appointment: Appointment)

    fun onAppointmentItemDeleteClick(appointment: Appointment)

    fun onError(exception: Exception?)
}