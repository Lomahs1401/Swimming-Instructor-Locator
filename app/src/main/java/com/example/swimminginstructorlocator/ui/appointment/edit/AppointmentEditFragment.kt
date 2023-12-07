package com.example.swimminginstructorlocator.ui.appointment.edit

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.swimminginstructorlocator.data.model.Appointment
import com.example.swimminginstructorlocator.databinding.FragmentAppointmentEditBinding
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingFragment
import com.example.swimminginstructorlocator.utils.ext.goBackFragment


class AppointmentEditFragment : BaseViewBindingFragment<FragmentAppointmentEditBinding>()
, AppointmentEditContract.View {
    private lateinit var appointmentEditPresenter: AppointmentEditPresenter
    companion object {
        private var appointment: Appointment? = null
        @JvmStatic
        fun newInstance() = AppointmentEditFragment()

        @JvmStatic
        fun setAppointmentDetail(appointment: Appointment) {
            this.appointment = appointment
        }
    }

    override fun onAppointmentEdit(appointment: Appointment) {
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    override fun createBindingFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAppointmentEditBinding {
        return FragmentAppointmentEditBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        appointmentEditPresenter = AppointmentEditPresenter()
        appointmentEditPresenter.setView(this)
    }

    override fun initData() {
        binding.imgBackButton.setOnClickListener {
            goBackFragment()
        }
        binding.btnSave.setOnClickListener {
            val date = binding.etDate.text.toString()
            val startTime = binding.etStartTime.text.toString()
            val endTime = binding.etEndTime.text.toString()
            SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("Do you want to save this appointment?")
                .setConfirmText("Yes")
                .setConfirmClickListener {
//                    appointmentEditPresenter.editAppointment(appointment?.id, date, startTime, endTime)
                    it.dismissWithAnimation()
                }
                .setCancelButton(
                    "No"
                ) { sDialog -> sDialog.dismissWithAnimation() }
                .show()
        }
        binding.etDate.setText(appointment?.date)
        binding.etStartTime.setText(appointment?.startTime)
        binding.etEndTime.setText(appointment?.endTime)
        binding.tvInstructorName.text = appointment?.teacherName
        binding.tvCourseName.text = appointment?.courseName
        binding.tvCourseLocation.text = "Location: " + appointment?.centerAddress
    }

}