package com.example.swimminginstructorlocator.ui.course.list

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.swimminginstructorlocator.R
import com.example.swimminginstructorlocator.databinding.FragmentCourseListBinding
import com.example.swimminginstructorlocator.ui.instructor.detail.InstructorDetailFragment
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingFragment
import java.lang.Exception

class CourseListFragment : BaseViewBindingFragment<FragmentCourseListBinding>(),  CourseListContract.View{
    override fun onSearchInstructor() {
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    override fun createBindingFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCourseListBinding {
        return FragmentCourseListBinding.inflate(inflater, container, false)
    }

    override fun initData() {
    }

    override fun initView() {
    }
    companion object {
        @JvmStatic
        fun newInstance() = InstructorDetailFragment()
    }
}