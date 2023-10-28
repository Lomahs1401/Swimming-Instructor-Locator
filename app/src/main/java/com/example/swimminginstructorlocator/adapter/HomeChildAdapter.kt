package com.example.swimminginstructorlocator.adapter

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swimminginstructorlocator.data.model.HomeChild
import com.example.swimminginstructorlocator.databinding.LayoutCenterHomeChildBinding
import com.example.swimminginstructorlocator.databinding.LayoutCourseHomeChildBinding
import com.example.swimminginstructorlocator.databinding.LayoutInstructorHomeChildBinding
import com.example.swimminginstructorlocator.listener.OnInstructorItemClickListener
import com.example.swimminginstructorlocator.ui.home.HomePresenter
import com.example.swimminginstructorlocator.utils.base.BaseViewHolder

class HomeChildAdapter(
    private val resources: Resources,
    private val presenter: HomePresenter,
    private val instructorItemClickListener: OnInstructorItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listHomeChild: MutableList<HomeChild> = mutableListOf()

    private val centerAdapter: CenterAdapter by lazy {
        CenterAdapter()
    }

    private val courseAdapter: CourseAdapter by lazy {
        CourseAdapter()
    }

    private val instructorAdapter: InstructorAdapter by lazy {
        InstructorAdapter(instructorItemClickListener)
    }

    override fun getItemViewType(position: Int): Int {
        val homeChild = listHomeChild[position]
        return homeChild.viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HOME_CENTER_VIEW -> {
                HomeCenterViewHolder(
                    LayoutCenterHomeChildBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            HOME_COURSE_VIEW -> {
                HomeCourseViewHolder(
                    LayoutCourseHomeChildBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else -> {
                HomeInstructorViewHolder(
                    LayoutInstructorHomeChildBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return listHomeChild.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BaseViewHolder).bindHomeChildData(listHomeChild[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listHomeChild: MutableList<HomeChild>) {
        this.listHomeChild = listHomeChild
        notifyDataSetChanged()
    }

    inner class HomeCenterViewHolder(private val binding: LayoutCenterHomeChildBinding) :
        BaseViewHolder(binding) {
        override fun bindHomeChildData(itemBinding: HomeChild) {
//            TODO("Not yet implemented")
        }
    }

    inner class HomeCourseViewHolder(private val binding: LayoutCourseHomeChildBinding) :
        BaseViewHolder(binding) {
        override fun bindHomeChildData(itemBinding: HomeChild) {
//            TODO("Not yet implemented")
        }
    }

    inner class HomeInstructorViewHolder(private val binding: LayoutInstructorHomeChildBinding) :
        BaseViewHolder(binding) {
        override fun bindHomeChildData(itemBinding: HomeChild) {
//            TODO("Not yet implemented")
        }
    }

    companion object {
        const val HOME_CENTER_VIEW = 1
        const val HOME_COURSE_VIEW = 2
        const val HOME_INSTRUCTOR_VIEW = 3
    }
}