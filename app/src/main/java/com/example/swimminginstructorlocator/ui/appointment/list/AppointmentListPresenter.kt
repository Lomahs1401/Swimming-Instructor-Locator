package com.example.swimminginstructorlocator.ui.appointment.list

import android.util.Log
import com.example.swimminginstructorlocator.data.model.Appointment
import com.example.swimminginstructorlocator.data.service.impl.AppointmentServiceImpl
import com.example.swimminginstructorlocator.listener.OnResultListener
import com.example.swimminginstructorlocator.utils.DataLocalManager

class AppointmentListPresenter(
    private val appointmentService: AppointmentServiceImpl
) : AppointmentListContract.Presenter {

    private var view: AppointmentListContract.View? = null
    override fun appointmentList() {
        val user  = DataLocalManager.getUser()
        Log.d("User", user.toString())
//        val userId = user?.id
        val userId = "655dc17b853f2c4d569ddadb"
        if (userId != null) {
            appointmentService.getAppointmentByUserId(
                userId,
                object : OnResultListener<MutableList<Appointment>> {
                    override fun onSuccess(dataResult: MutableList<Appointment>) {
                        view?.onAppointmentList(dataResult)
                    }

                    override fun onError(exception: Exception?) {
                        view?.onError(exception)
                    }
                }
            )
        }
    }

    override fun deleteAppointment(appointmentId: String) {
        appointmentService.deleteAppointment(
            appointmentId,
            object : OnResultListener<Boolean> {
                override fun onSuccess(dataResult: Boolean) {
                    view?.onDeletedAppointment(dataResult)
                }

                override fun onError(exception: Exception?) {
                    view?.onError(exception)
                }
            }
        )
    }

    override fun onStart() {

    }

    override fun onStop() {
    }

    override fun setView(view: AppointmentListContract.View?) {
        this.view = view
    }
}