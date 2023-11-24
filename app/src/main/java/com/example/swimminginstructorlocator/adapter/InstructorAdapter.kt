package com.example.swimminginstructorlocator.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.databinding.ItemInstructorBinding
import com.example.swimminginstructorlocator.listener.OnImageClickListener
import com.example.swimminginstructorlocator.utils.ext.loadImageWithUrl
import com.example.swimminginstructorlocator.utils.ext.notNull

class InstructorAdapter(
    private val itemClickListener: OnImageClickListener
) : RecyclerView.Adapter<InstructorAdapter.InstructorViewHolder>() {

    private var listInstructors: MutableList<Instructor> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructorViewHolder {
        val binding =
            ItemInstructorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InstructorViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return minOf(MAX_ITEM_SHOW_REVIEW, listInstructors.size)
    }

    override fun onBindViewHolder(holder: InstructorViewHolder, position: Int) {
        holder.bindData(listInstructors[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listInstructors: MutableList<Instructor>) {
        this.listInstructors = listInstructors
        notifyDataSetChanged()
    }

    inner class InstructorViewHolder(private val binding: ItemInstructorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(instructor: Instructor) {
            binding.instructorName.text = instructor.instructorName
            instructor.image.notNull {
                binding.instructorImg.loadImageWithUrl(it)
            }

            binding.instructorImg.setOnClickListener {
                itemClickListener.onImageClick(instructor)
            }
        }
    }

    companion object {
        private const val MAX_ITEM_SHOW_REVIEW = 6
    }
}