package com.example.swimminginstructorlocator.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swimminginstructorlocator.data.model.Center
import com.example.swimminginstructorlocator.databinding.ItemViewMoreCenterBinding
import com.example.swimminginstructorlocator.listener.OnItemClickListener
import com.example.swimminginstructorlocator.utils.ext.loadImageWithUrl
import com.example.swimminginstructorlocator.utils.ext.notNull

class ViewMoreCenterAdapter(
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ViewMoreCenterAdapter.ViewMoreCenterViewHolder>() {

    private var listCenters: MutableList<Center> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewMoreCenterViewHolder {
        val binding =
            ItemViewMoreCenterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewMoreCenterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listCenters.size
    }

    override fun onBindViewHolder(holder: ViewMoreCenterViewHolder, position: Int) {
        holder.bindData(listCenters[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listCenters: MutableList<Center>) {
        this.listCenters = listCenters
        notifyDataSetChanged()
    }

    inner class ViewMoreCenterViewHolder(private val binding: ItemViewMoreCenterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindData(center: Center) {
            binding.tvCenterName.text = center.centerName
            binding.tvCenterAddress.text = center.address
            binding.tvCenterPhone.text = center.phone
            binding.tvCenterEmail.text = center.email

            center.image.notNull {
                binding.imgCenter.loadImageWithUrl(center.image)
            }
        }
    }
}
