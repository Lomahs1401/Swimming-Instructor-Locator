package com.example.swimminginstructorlocator.ui.course.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.swimminginstructorlocator.R
import com.example.swimminginstructorlocator.data.model.CourseDetail
import com.example.swimminginstructorlocator.databinding.FragmentCourseDetailBinding
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingFragment
import com.example.swimminginstructorlocator.utils.ext.goBackFragment
import com.example.swimminginstructorlocator.utils.ext.loadImageWithUrl
import com.example.swimminginstructorlocator.utils.ext.notNull
import java.lang.Exception

class CourseDetailFragment : BaseViewBindingFragment<FragmentCourseDetailBinding>(), CourseDetailContract.View {

    private lateinit var courseDetailPresenter: CourseDetailPresenter
    override fun createBindingFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCourseDetailBinding {
        return FragmentCourseDetailBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        courseDetailPresenter = CourseDetailPresenter()
        courseDetailPresenter.setView(this)
    }

    @SuppressLint("SetTextI18n")
    override fun initData() {
        binding.tvCourseName.text = courseDetail?.courseName
        binding.tvCourseDescription.text = courseDetail?.description
        binding.tvCourseDate.text = courseDetail?.schedule

        courseDetail?.image.notNull {
            courseDetail?.image?.let { value -> binding.imgCourse.loadImageWithUrl(value) }
        }

        binding.imgBackButton.setOnClickListener {
            goBackFragment()
        }
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private var courseDetail: CourseDetail? = null
        @JvmStatic
        fun newInstance() = CourseDetailFragment()

        @JvmStatic
        fun setCourseDetail(courseDetail: CourseDetail) {
            this.courseDetail = courseDetail
        }
    }
}