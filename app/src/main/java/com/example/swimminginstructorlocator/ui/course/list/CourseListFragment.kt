package com.example.swimminginstructorlocator.ui.course.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.swimminginstructorlocator.R
import com.example.swimminginstructorlocator.adapter.CourseAdapter
import com.example.swimminginstructorlocator.data.model.Course
import com.example.swimminginstructorlocator.data.model.CourseDetail
import com.example.swimminginstructorlocator.data.repo.CourseRepo
import com.example.swimminginstructorlocator.data.service.CourseService
import com.example.swimminginstructorlocator.databinding.FragmentCourseListBinding
import com.example.swimminginstructorlocator.listener.OnImageClickListener
import com.example.swimminginstructorlocator.ui.course.detail.CourseDetailFragment
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingFragment
import com.example.swimminginstructorlocator.utils.ext.addFragment
import com.example.swimminginstructorlocator.utils.ext.goBackFragment
import java.lang.Exception

class CourseListFragment : BaseViewBindingFragment<FragmentCourseListBinding>(),  CourseListContract.View, OnImageClickListener {

    private lateinit var courseListPresenter: CourseListPresenter
    private var progressDialog: SweetAlertDialog? = null

    private val courseAdapter: CourseAdapter by lazy {
        CourseAdapter(onItemClickListener = this)
    }

    // ----------------------     Base View Binding Fragment     ----------------------

    override fun createBindingFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCourseListBinding {
        return FragmentCourseListBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        courseListPresenter = CourseListPresenter(
            CourseService.getInstance(CourseRepo.getInstance())
        )
        courseListPresenter.setView(this)
    }

    override fun initData() {
        courseAdapter.setData(listCourse)
        binding.rcvCourse.adapter = courseAdapter

        binding.imgBackButton.setOnClickListener {
            goBackFragment()
        }
    }

    // ----------------------     On Item Click Listener     ----------------------

    override fun onImageClick(item: Any) {
        if (item is Course) {
            progressDialog = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText(R.string.loading)
                .apply {
                    setCancelable(false)
                    show()
                }
            courseListPresenter.getCourseDetail(item.id)
        }
    }

    // ----------------------     Handle Click Search     ----------------------

    override fun onSearchInstructor() {
    }

    override fun onGetCourseDetail(course: CourseDetail) {
        progressDialog?.dismissWithAnimation()

        CourseDetailFragment.setCourseDetail(course)
        val courseDetail = CourseDetailFragment.newInstance()
        addFragment(
            R.id.fragment_home_container,
            courseDetail,
            addToBackStack = true
        )
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private var listCourse: MutableList<Course> = mutableListOf()

        @JvmStatic
        fun newInstance() = CourseListFragment()

        @JvmStatic
        fun setListCourse(listCourse: MutableList<Course>) {
            this.listCourse = listCourse
        }
    }
}