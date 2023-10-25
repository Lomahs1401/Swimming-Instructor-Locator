package com.example.swimminginstructorlocator.utils.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<T : Any>(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bindData(itemBinding: T)
}
