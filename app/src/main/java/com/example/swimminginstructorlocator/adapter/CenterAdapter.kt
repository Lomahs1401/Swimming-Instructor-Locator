package com.example.swimminginstructorlocator.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swimminginstructorlocator.data.model.Center
import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.databinding.ItemCenterBinding

class CenterAdapter : RecyclerView.Adapter<CenterAdapter.CenterViewHolder>() {

    private var listCenters: MutableList<Center> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CenterViewHolder {
        val binding = ItemCenterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CenterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listCenters.size
    }

    override fun onBindViewHolder(holder: CenterViewHolder, position: Int) {
        holder.bindData(listCenters[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listCenters: MutableList<Center>) {
        this.listCenters = listCenters
        notifyDataSetChanged()
    }

    inner class CenterViewHolder(private val binding: ItemCenterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(center: Center) {

        }
    }
}