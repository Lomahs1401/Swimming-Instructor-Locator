package com.example.swimminginstructorlocator.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swimminginstructorlocator.data.model.Center
import com.example.swimminginstructorlocator.data.model.HomeChild
import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.databinding.LayoutCenterHomeChildBinding
import com.example.swimminginstructorlocator.databinding.LayoutInstructorHomeChildBinding
import com.example.swimminginstructorlocator.listener.OnItemClickListener
import com.example.swimminginstructorlocator.ui.home.HomePresenter
import com.example.swimminginstructorlocator.utils.base.BaseViewHolder

class HomeChildAdapter(
    private val presenter: HomePresenter,
    private val itemClickListener: OnItemClickListener,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listHomeChild: MutableList<HomeChild> = mutableListOf()

    private val centerAdapter: CenterAdapter by lazy {
        CenterAdapter()
    }

    private val instructorAdapter: InstructorAdapter by lazy {
        InstructorAdapter(itemClickListener)
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

    fun setCenters(listCenters: MutableList<Center>) {
        print(listCenters.size)
        centerAdapter.setData(listCenters)
    }

    fun setInstructors(listInstructors: MutableList<Instructor>) {
        print(listInstructors.size)
        instructorAdapter.setData(listInstructors)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listHomeChild: MutableList<HomeChild>) {
        this.listHomeChild = listHomeChild
        notifyDataSetChanged()
    }

    inner class HomeCenterViewHolder(private val binding: LayoutCenterHomeChildBinding) :
        BaseViewHolder(binding) {
        override fun bindHomeChildData(itemBinding: HomeChild) {
            binding.rcvCenter.adapter = centerAdapter

            binding.tvSeeAll.setOnClickListener {
                presenter.viewMoreCenters()
            }
        }
    }

    inner class HomeInstructorViewHolder(private val binding: LayoutInstructorHomeChildBinding) :
        BaseViewHolder(binding) {
        override fun bindHomeChildData(itemBinding: HomeChild) {
            binding.rcvInstructor.adapter = instructorAdapter

            binding.tvSeeAll.setOnClickListener {
                presenter.viewMoreInstructors()
            }
        }
    }

    companion object {
        const val HOME_CENTER_VIEW = 1
        const val HOME_INSTRUCTOR_VIEW = 2
    }
}