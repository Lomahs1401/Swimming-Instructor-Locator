package com.example.swimminginstructorlocator.utils.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.swimminginstructorlocator.data.model.HomeChild

abstract class BaseViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bindHomeChildData(itemBinding: HomeChild)
}
