package com.example.swimminginstructorlocator.ui.course.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swimminginstructorlocator.R
import com.example.swimminginstructorlocator.databinding.FragmentCourseDetailBinding
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingFragment

class CourseDetailFragment : BaseViewBindingFragment<FragmentCourseDetailBinding>() {
    override fun createBindingFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCourseDetailBinding {
        return FragmentCourseDetailBinding.inflate(inflater, container, false)
    }

    override fun initView() {
//        TODO("Not yet implemented")
    }

    override fun initData() {
//        TODO("Not yet implemented")
    }
}