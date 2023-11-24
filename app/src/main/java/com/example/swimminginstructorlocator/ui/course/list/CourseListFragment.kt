package com.example.swimminginstructorlocator.ui.course.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.swimminginstructorlocator.databinding.FragmentCourseListBinding
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

    override fun initView() {
    }

    override fun initData() {
    }

    companion object {
        @JvmStatic
        fun newInstance() = CourseListFragment()
    }
}