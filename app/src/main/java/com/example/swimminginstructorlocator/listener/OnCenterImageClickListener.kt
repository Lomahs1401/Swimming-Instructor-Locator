package com.example.swimminginstructorlocator.listener

import com.example.swimminginstructorlocator.data.model.Center

interface OnCenterImageClickListener : OnImageClickListener {
    fun onCenterImageClick(center: Center)
}