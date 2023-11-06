package com.example.swimminginstructorlocator.ui.home

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.swimminginstructorlocator.R
import com.example.swimminginstructorlocator.adapter.HomeChildAdapter
import com.example.swimminginstructorlocator.data.model.Center
import com.example.swimminginstructorlocator.data.model.HomeChild
import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.data.repo.CenterRepo
import com.example.swimminginstructorlocator.data.repo.InstructorRepo
import com.example.swimminginstructorlocator.data.service.InstructorService
import com.example.swimminginstructorlocator.data.service.CenterService
import com.example.swimminginstructorlocator.databinding.FragmentHomeBinding
import com.example.swimminginstructorlocator.listener.OnItemClickListener
import com.example.swimminginstructorlocator.ui.viewMore.viewMoreCenter.ViewMoreCenterFragment
import com.example.swimminginstructorlocator.ui.viewMore.viewMoreInstructor.ViewMoreInstructorFragment
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingFragment
import com.example.swimminginstructorlocator.utils.ext.addFragment
import java.lang.Exception

class HomeFragment : BaseViewBindingFragment<FragmentHomeBinding>(), HomeContract.View, OnItemClickListener {

    private lateinit var dialog: ProgressDialog
    private lateinit var homePresenter: HomePresenter

    private var listCenters: MutableList<Center> = mutableListOf()
    private var listInstructors: MutableList<Instructor> = mutableListOf()

    private val homeChildAdapter: HomeChildAdapter by lazy {
        HomeChildAdapter(presenter = homePresenter, itemClickListener = this)
    }

    override fun createBindingFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initData() {
        homePresenter = HomePresenter(
            InstructorService.getInstance(InstructorRepo.getInstance()),
            CenterService.getInstance(CenterRepo.getInstance())
        )
        homePresenter.setView(this)
        homePresenter.getListInstructors()
        homePresenter.getListCenters()

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
        this.listCenters = listCenters
        homeChildAdapter.setCenters(listCenters)
        dialog.dismiss()
    }

    override fun onGetListInstructors(listInstructors: MutableList<Instructor>) {
        this.listInstructors = listInstructors
        homeChildAdapter.setInstructors(listInstructors)
        dialog.dismiss()
    }

    override fun onViewMoreCenters() {
        val viewMoreCenterFragment = ViewMoreCenterFragment.newInstance(listCenters)
        addFragment(
            R.id.fragment_home_container,
            viewMoreCenterFragment,
            addToBackStack = true
        )
    }

    override fun onViewMoreInstructors() {
        val viewMoreInstructorFragment = ViewMoreInstructorFragment.newInstance(listInstructors)
        addFragment(
            R.id.fragment_home_container,
            viewMoreInstructorFragment,
            addToBackStack = true
        )
    }

    override fun onCenterImageClick(center: Center) {
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
            HomeChild(HomeChildAdapter.HOME_INSTRUCTOR_VIEW)
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}