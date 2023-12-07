package com.example.swimminginstructorlocator.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.swimminginstructorlocator.data.model.Appointment
import com.example.swimminginstructorlocator.databinding.ItemAppointmentBinding
import com.example.swimminginstructorlocator.listener.OnImageClickListener
import com.example.swimminginstructorlocator.listener.OnResultListener

class AppointmentAdapter(
    private val onItemClickListener: OnResultListener<Appointment>
): RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {

    private var listAppointments: MutableList<Appointment> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val binding = ItemAppointmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AppointmentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return minOf(MAX_ITEM_SHOW_REVIEW, listAppointments.size)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        holder.bindData(listAppointments[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listAppointments: MutableList<Appointment>) {
        this.listAppointments = listAppointments
        notifyDataSetChanged()
    }

    inner class AppointmentViewHolder(private val binding: ItemAppointmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(appointment: Appointment) {
            binding.tvCourseName.text = appointment.courseName
            binding.tvCourseInstructor.text = appointment.teacherName
            binding.tvAppointmentDate.text = "Date: " + appointment.date
            binding.tvAppointmentTime.text = "Time: " + appointment.startTime + " - " + appointment.endTime
            binding.tvAppointmentLocation.text = "Location: " + appointment.centerAddress

            binding.btnDelete.setOnClickListener{
                onItemClickListener.onSuccess(appointment)
            }
        }
    }

    companion object {
        private const val MAX_ITEM_SHOW_REVIEW = 5
    }
}
