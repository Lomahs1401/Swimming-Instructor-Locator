package com.example.swimminginstructorlocator.ui.home

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.swimminginstructorlocator.R
import com.example.swimminginstructorlocator.adapter.HomeChildAdapter
import com.example.swimminginstructorlocator.data.model.Center
import com.example.swimminginstructorlocator.data.model.Course
import com.example.swimminginstructorlocator.data.model.HomeChild
import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.data.repo.InstructorRepo
import com.example.swimminginstructorlocator.data.service.InstructorService
import com.example.swimminginstructorlocator.databinding.FragmentHomeBinding
import com.example.swimminginstructorlocator.listener.OnInstructorItemClickListener
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingFragment
import java.lang.Exception

class HomeFragment : BaseViewBindingFragment<FragmentHomeBinding>(), HomeContract.View, OnInstructorItemClickListener {

    private lateinit var dialog: ProgressDialog
    private lateinit var homePresenter: HomePresenter

    private var listCenters: MutableList<Center> = mutableListOf()
    private var listCourses: MutableList<Course> = mutableListOf()
    private var listInstructors: MutableList<Instructor> = mutableListOf()

    private val homeChildAdapter: HomeChildAdapter by lazy {
        HomeChildAdapter(instructorItemClickListener = this)
    }

    override fun createBindingFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initData() {
        homePresenter = HomePresenter(
            InstructorService.getInstance(InstructorRepo.getInstance())
        )
        homePresenter.setView(this)
        homePresenter.getListInstructors()

        dialog = ProgressDialog(context).apply {
            setTitle(getString(R.string.loading))
            show()
        }
    }

    override fun initView() {
        homeChildAdapter.setData(getListHomeChild())
        binding.rcvHomeParent.adapter = homeChildAdapter

    }

    override fun onGetListCenters(listCenters: MutableList<Center>) {
//        TODO("Not yet implemented")
    }

    override fun onGetListCourses(listCourse: MutableList<Course>) {
//        TODO("Not yet implemented")
    }

    override fun onGetListInstructors(listInstructors: MutableList<Instructor>) {
        this.listInstructors = listInstructors
        homeChildAdapter.setInstructors(listInstructors)

        dialog.dismiss()
    }

    override fun onViewMoreCenters() {
//        TODO("Not yet implemented")
    }

    override fun onViewMoreCourses() {
//        TODO("Not yet implemented")
    }

    override fun onViewMoreInstructors() {
//        TODO("Not yet implemented")
    }

    override fun onInstructorImageClick(instructor: Instructor) {
//        TODO("Not yet implemented")
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        homeChildAdapter.notifyDataSetChanged()
    }

    private fun getListHomeChild(): MutableList<HomeChild> {
        return mutableListOf(
            HomeChild(HomeChildAdapter.HOME_CENTER_VIEW),
            HomeChild(HomeChildAdapter.HOME_COURSE_VIEW),
            HomeChild(HomeChildAdapter.HOME_INSTRUCTOR_VIEW)
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}