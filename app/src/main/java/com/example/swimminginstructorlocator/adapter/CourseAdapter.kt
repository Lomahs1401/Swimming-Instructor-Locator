package com.example.swimminginstructorlocator.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swimminginstructorlocator.data.model.Course
import com.example.swimminginstructorlocator.databinding.ItemCourseBinding
import com.example.swimminginstructorlocator.listener.OnImageClickListener
import com.example.swimminginstructorlocator.utils.ext.loadImageWithUrl
import com.example.swimminginstructorlocator.utils.ext.notNull

class CourseAdapter(
    private val onItemClickListener: OnImageClickListener
) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    private var listCourse: MutableList<Course> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listCourse.size
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bindData(listCourse[position])
    }

    fun setData(listCourse: MutableList<Course>) {
        this.listCourse = listCourse
    }

    inner class CourseViewHolder(private val binding: ItemCourseBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(course: Course) {
            binding.tvCourseName.text = course.courseName
            binding.tvCourseInstructor.text = course.instructor.instructorName
            binding.tvCourseDate.text = course.schedule
            binding.tvCourseDescription.text = course.description

            course.image.notNull {
                binding.courseImg.loadImageWithUrl(course.image)
            }

            binding.root.setOnClickListener {
                onItemClickListener.onImageClick(course)
            }
        }
    }
}