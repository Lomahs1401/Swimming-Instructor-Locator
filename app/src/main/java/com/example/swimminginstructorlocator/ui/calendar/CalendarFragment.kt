package com.example.swimminginstructorlocator.ui.calendar

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.swimminginstructorlocator.R
import com.example.swimminginstructorlocator.data.model.Appointment
import com.example.swimminginstructorlocator.data.repo.AppointmentRepo
import com.example.swimminginstructorlocator.data.service.AppointmentService
import com.example.swimminginstructorlocator.databinding.FragmentCalendarBinding
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingFragment
import java.lang.Exception


class CalendarFragment : BaseViewBindingFragment<FragmentCalendarBinding>(), CalendarContract.View {

    private lateinit var calendarPresenter: CalendarPresenter
    private var progressDialog: SweetAlertDialog? = null

    override fun createBindingFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCalendarBinding {
        return FragmentCalendarBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        calendarPresenter = CalendarPresenter(
            AppointmentService.getInstance(AppointmentRepo.getInstance())
        )
        calendarPresenter.setView(this)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun initData() {
        binding.apply {
            etDate.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_UP) {
                    etDate.inputType = android.text.InputType.TYPE_NULL
                    val datePickerFragment = DatePickerFragment()
                    val supportFragmentManager = requireActivity().supportFragmentManager

                    supportFragmentManager.setFragmentResultListener(
                        "REQUEST_KEY",
                        viewLifecycleOwner
                    ) { resultKey, bundle ->
                        if (resultKey == "REQUEST_KEY") {
                            val date = bundle.getString("SELECTED_DATE")
                            etDate.setText(date)
                        }
                    }
                    datePickerFragment.show(supportFragmentManager, "datePicker")
                }
                false
            }
            etStartTime.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_UP) {
                    etStartTime.inputType = android.text.InputType.TYPE_NULL
                    val timePickerFragment = TimePickerFragment()
                    val supportFragmentManager = requireActivity().supportFragmentManager

                    supportFragmentManager.setFragmentResultListener(
                        "REQUEST_KEY",
                        viewLifecycleOwner
                    ) { resultKey, bundle ->
                        if (resultKey == "REQUEST_KEY") {
                            val time = bundle.getString("SELECTED_TIME")
                            etStartTime.setText(time)
                        }
                    }
                    timePickerFragment.show(supportFragmentManager, "timePicker")
                }
                false
            }
            etEndTime.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_UP) {
                    etEndTime.inputType = android.text.InputType.TYPE_NULL
                    val timePickerFragment = TimePickerFragment()
                    val supportFragmentManager = requireActivity().supportFragmentManager

                    supportFragmentManager.setFragmentResultListener(
                        "REQUEST_KEY",
                        viewLifecycleOwner
                    ) { resultKey, bundle ->
                        if (resultKey == "REQUEST_KEY") {
                            val time = bundle.getString("SELECTED_TIME")
                            etEndTime.setText(time)
                        }
                    }
                    timePickerFragment.show(supportFragmentManager, "timePicker")
                }
                false
            }

            btnBook.setOnClickListener {
                val startTime = etStartTime.text.toString()
                val endTime = etEndTime.text.toString()
                val date = etDate.text.toString()

                progressDialog = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
                    .setTitleText(R.string.loading)
                    .apply {
                        setCancelable(false)
                        show()
                    }

                calendarPresenter.createCalendar(date, startTime, endTime)
            }
        }
    }

    override fun onCreateCalendar(appointment: Appointment) {
        SweetAlertDialog(requireContext(), SweetAlertDialog.SUCCESS_TYPE).apply {
            titleText = getString(R.string.booking_successful)
            contentText = getString(
                R.string.session_booking_message,
                appointment.date,
                appointment.startTime,
                appointment.endTime
            )
            setConfirmClickListener {
                dismissWithAnimation()
            }
            show()
        }

        progressDialog?.dismissWithAnimation()
    }

    override fun onError(exception: Exception?) {
//        TODO("Not yet implemented")
    }

    companion object {
        @JvmStatic
        fun newInstance() = CalendarFragment()
    }
}