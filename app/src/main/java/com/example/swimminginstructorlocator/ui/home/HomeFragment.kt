package com.example.swimminginstructorlocator.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.swimminginstructorlocator.R
import com.example.swimminginstructorlocator.adapter.HomeChildAdapter
import com.example.swimminginstructorlocator.data.model.Center
import com.example.swimminginstructorlocator.data.model.CenterDetail
import com.example.swimminginstructorlocator.data.model.HomeChild
import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.data.model.InstructorDetail
import com.example.swimminginstructorlocator.data.repo.CenterRepo
import com.example.swimminginstructorlocator.data.repo.InstructorRepo
import com.example.swimminginstructorlocator.data.service.InstructorService
import com.example.swimminginstructorlocator.data.service.CenterService
import com.example.swimminginstructorlocator.databinding.FragmentHomeBinding
import com.example.swimminginstructorlocator.listener.OnCenterImageClickListener
import com.example.swimminginstructorlocator.listener.OnInstructorImageClickListener
import com.example.swimminginstructorlocator.ui.center.detail.CenterDetailFragment
import com.example.swimminginstructorlocator.ui.instructor.detail.InstructorDetailFragment
import com.example.swimminginstructorlocator.ui.viewMore.viewMoreCenter.ViewMoreCenterFragment
import com.example.swimminginstructorlocator.ui.viewMore.viewMoreInstructor.ViewMoreInstructorFragment
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingFragment
import com.example.swimminginstructorlocator.utils.ext.addFragment

class HomeFragment : BaseViewBindingFragment<FragmentHomeBinding>(), HomeContract.View,
    OnCenterImageClickListener, OnInstructorImageClickListener {

    private lateinit var homePresenter: HomePresenter
    private var progressDialog: SweetAlertDialog? = null


    private var listCenters: MutableList<Center> = mutableListOf()
    private var listInstructors: MutableList<Instructor> = mutableListOf()

    private val homeChildAdapter: HomeChildAdapter by lazy {
        HomeChildAdapter(presenter = homePresenter, imageClickListener = this)
    }

    // ----------------------     Base View Binding Fragment     ----------------------

    override fun createBindingFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        homePresenter = HomePresenter(
            InstructorService.getInstance(InstructorRepo.getInstance()),
            CenterService.getInstance(CenterRepo.getInstance())
        )
        homePresenter.setView(this)
    }

    override fun initData() {
        homePresenter.getListInstructors()
        homePresenter.getListCenters()

        homeChildAdapter.setData(getListHomeChild())
        binding.rcvHomeParent.adapter = homeChildAdapter

        progressDialog = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
            .setTitleText("Loading...")
            .apply {
                setCancelable(false)
                show()
            }
    }

    // ----------------------     On Item Click Listener     ----------------------

    override fun onCenterImageClick(center: Center) {
        progressDialog = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
            .setTitleText("Loading...")
            .apply {
                setCancelable(false)
                show()
            }

        homePresenter.getCenterDetail(center.id)
    }
    override fun onInstructorImageClick(instructor: Instructor) {
        progressDialog = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
            .setTitleText("Loading...")
            .apply {
                setCancelable(false)
                show()
            }

        homePresenter.getInstructorDetail(instructor.id)
    }
    override fun onImageClick(item: Any) {
        when (item) {
            is Center -> onCenterImageClick(item)
            is Instructor -> onInstructorImageClick(item)
        }
    }

    // ----------------------     Home Contract View     ----------------------
    override fun onGetListCenters(listCenters: MutableList<Center>) {
        this.listCenters = listCenters
        homeChildAdapter.setCenters(listCenters)
        progressDialog?.dismissWithAnimation()
    }

    override fun onGetListInstructors(listInstructors: MutableList<Instructor>) {
        this.listInstructors = listInstructors
        homeChildAdapter.setInstructors(listInstructors)
        progressDialog?.dismissWithAnimation()

    }
    override fun onGetCenterDetail(centerDetail: CenterDetail) {
        progressDialog?.dismissWithAnimation()

        val centerDetailFragment = CenterDetailFragment.newInstance(centerDetail)
        addFragment(
            R.id.fragment_home_container,
            centerDetailFragment,
            addToBackStack = true
        )
    }

    override fun onGetInstructorDetail(instructorDetail: InstructorDetail) {
        progressDialog?.dismissWithAnimation()

        val instructorDetailFragment = InstructorDetailFragment.newInstance(instructorDetail)
        addFragment(
            R.id.fragment_home_container,
            instructorDetailFragment,
            addToBackStack = true
        )
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