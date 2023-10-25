package com.example.swimminginstructorlocator.utils.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.swimminginstructorlocator.R

fun ImageView.loadImageWithUrl(url: String) {
    Glide.with(this)
        .load(url)
        .placeholder(R.mipmap.ic_launcher)
        .into(this)
}
