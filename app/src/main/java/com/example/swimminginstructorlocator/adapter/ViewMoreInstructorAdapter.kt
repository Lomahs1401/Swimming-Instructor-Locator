package com.example.swimminginstructorlocator.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.databinding.ItemViewMoreInstructorBinding
import com.example.swimminginstructorlocator.listener.OnItemClickListener
import com.example.swimminginstructorlocator.utils.ext.loadImageWithUrl
import com.example.swimminginstructorlocator.utils.ext.notNull

class ViewMoreInstructorAdapter(
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ViewMoreInstructorAdapter.ViewMoreInstructorViewHolder>() {

    private var listInstructors: MutableList<Instructor> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewMoreInstructorViewHolder {
        val binding =
            ItemViewMoreInstructorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewMoreInstructorViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listInstructors.size
    }

    override fun onBindViewHolder(holder: ViewMoreInstructorViewHolder, position: Int) {
        holder.bindData(listInstructors[position])
    }

    fun setData(listInstructors: MutableList<Instructor>) {
        this.listInstructors = listInstructors
    }

    inner class ViewMoreInstructorViewHolder(private val binding: ItemViewMoreInstructorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindData(instructor: Instructor) {
            instructor.image.notNull {
                binding.imgInstructor.loadImageWithUrl(it)
            }
            binding.tvName.text = instructor.instructorName
            binding.tvEmail.text = instructor.instructorName
            binding.tvPhone.text = "000.000.0000"
            binding.tvAddress.text = "123 Street, City, State, 00000"

            binding.imgInstructor.setOnClickListener {
                itemClickListener.onInstructorImageClick(instructor)
            }
        }
    }
}
