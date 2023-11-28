package com.example.swimminginstructorlocator.ui.calendar

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.Fragment
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.swimminginstructorlocator.R
import com.example.swimminginstructorlocator.databinding.FragmentCalendarBinding


class CalendarFragment : Fragment(R.layout.fragment_calendar) {
    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCalendarBinding.bind(view)

        binding.apply {
            etStartTime.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_UP) {
                    etStartTime.inputType = android.text.InputType.TYPE_NULL
                    val timePickerFragment = TimePickerFragment()
                    val supportFragmentManager = requireActivity().supportFragmentManager

                    supportFragmentManager.setFragmentResultListener(
                        "REQUEST_KEY",
                        viewLifecycleOwner
                    ) { resultKey, bundle ->
                        if(resultKey == "REQUEST_KEY") {
                            val time = bundle.getString("SELECTED_TIME")
                            etStartTime.setText(time)
                        }
                    }
                    timePickerFragment.show(supportFragmentManager, "timePicker")
                }
                false
            }
            etEndTime.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_UP) {
                    etEndTime.inputType = android.text.InputType.TYPE_NULL
                    val timePickerFragment = TimePickerFragment()
                    val supportFragmentManager = requireActivity().supportFragmentManager

                    supportFragmentManager.setFragmentResultListener(
                        "REQUEST_KEY",
                        viewLifecycleOwner
                    ) { resultKey, bundle ->
                        if(resultKey == "REQUEST_KEY") {
                            val time = bundle.getString("SELECTED_TIME")
                            etEndTime.setText(time)
                        }
                    }
                    timePickerFragment.show(supportFragmentManager, "timePicker")
                }
                false
            }
            etDate.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_UP) {
                    etDate.inputType = android.text.InputType.TYPE_NULL
                    val datePickerFragment = DatePickerFragment()
                    val supportFragmentManager = requireActivity().supportFragmentManager

                    supportFragmentManager.setFragmentResultListener(
                        "REQUEST_KEY",
                        viewLifecycleOwner
                    ) { resultKey, bundle ->
                        if(resultKey == "REQUEST_KEY") {
                            val date = bundle.getString("SELECTED_DATE")
                            etDate.setText(date)
                        }
                    }
                    datePickerFragment.show(supportFragmentManager, "datePicker")
                }
                false
            }

            btnBook.setOnClickListener {
                val startTime = etStartTime.text.toString()
                val endTime = etEndTime.text.toString()
                val date = etDate.text.toString()
                SweetAlertDialog(requireContext(), SweetAlertDialog.SUCCESS_TYPE).apply {
                    titleText = "Booking Successful"
                    contentText = "You have booked a session on $date from $startTime to $endTime"
                    setConfirmClickListener {
                        dismissWithAnimation()
                    }
                    show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = CalendarFragment()
    }
}