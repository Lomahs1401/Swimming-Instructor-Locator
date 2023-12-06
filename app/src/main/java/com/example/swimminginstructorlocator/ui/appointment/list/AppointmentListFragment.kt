package com.example.swimminginstructorlocator.ui.appointment.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.swimminginstructorlocator.R
import com.example.swimminginstructorlocator.adapter.AppointmentAdapter
import com.example.swimminginstructorlocator.data.model.Appointment
import com.example.swimminginstructorlocator.data.repo.AppointmentRepo
import com.example.swimminginstructorlocator.data.service.AppointmentService
import com.example.swimminginstructorlocator.databinding.FragmentAppointmentListBinding
import com.example.swimminginstructorlocator.listener.OnResultListener
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingFragment

class AppointmentListFragment : BaseViewBindingFragment<FragmentAppointmentListBinding>(),
    AppointmentListContract.View, OnResultListener<Appointment> {

    private lateinit var appointmentListPresenter: AppointmentListPresenter

    private  val appointmentListAdapter: AppointmentAdapter by lazy {
        AppointmentAdapter(this)
    }
    companion object {
        private var listAppointments: MutableList<Appointment> = mutableListOf()
        @JvmStatic
        fun newInstance() =
            AppointmentListFragment()
    }

    override fun onAppointmentList(listAppointment: MutableList<Appointment>) {
       if (listAppointment.size > 0){
           appointmentListAdapter.setData(listAppointment)
           binding.rcvAppointmentList.adapter = appointmentListAdapter
           binding.tvNoAppointmentFound.visibility = View.GONE
           binding.rcvAppointmentList.visibility = View.VISIBLE
       } else {
           binding.tvNoAppointmentFound.visibility = View.VISIBLE
           binding.rcvAppointmentList.visibility = View.GONE
       }
    }

    override fun onDeletedAppointment(boolean: Boolean) {
        if (boolean) {
            SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Deleted!")
                .setContentText("Your appointment has been deleted!")
                .show()
            appointmentListPresenter.appointmentList()
        } else
        {
            SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Oops...")
                .setContentText("Something went wrong!")
                .show()
        }
    }

    override fun onSuccess(dataResult: Appointment) {
        SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
            .setTitleText("Are you sure?")
            .setContentText("You won't be able to recover this appointment!")
            .setConfirmText("Yes, delete it!")
            .setConfirmClickListener {
                it.dismissWithAnimation()
                appointmentListPresenter.deleteAppointment(dataResult.id)
            }
            .show()
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    override fun createBindingFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAppointmentListBinding {
        return FragmentAppointmentListBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        appointmentListPresenter = AppointmentListPresenter(
            AppointmentService.getInstance(AppointmentRepo.getInstance())
        )
        appointmentListPresenter.setView(this)
        appointmentListPresenter.appointmentList()
    }

    override fun initData() {
        appointmentListAdapter.setData(listAppointments)
        binding.rcvAppointmentList.adapter = appointmentListAdapter


    }
}