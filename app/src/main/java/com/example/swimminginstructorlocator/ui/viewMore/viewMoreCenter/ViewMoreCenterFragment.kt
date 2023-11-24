package com.example.swimminginstructorlocator.ui.viewMore.viewMoreCenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.swimminginstructorlocator.R
import com.example.swimminginstructorlocator.adapter.ViewMoreCenterAdapter
import com.example.swimminginstructorlocator.data.model.Center
import com.example.swimminginstructorlocator.data.model.CenterDetail
import com.example.swimminginstructorlocator.data.repo.CenterRepo
import com.example.swimminginstructorlocator.data.service.CenterService
import com.example.swimminginstructorlocator.databinding.FragmentViewMoreCenterBinding
import com.example.swimminginstructorlocator.listener.OnCenterImageClickListener
import com.example.swimminginstructorlocator.ui.center.detail.CenterDetailFragment
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingFragment
import com.example.swimminginstructorlocator.utils.ext.addFragment
import com.example.swimminginstructorlocator.utils.ext.goBackFragment
import java.lang.Exception

class ViewMoreCenterFragment(
    private val listCenters: MutableList<Center>
) : BaseViewBindingFragment<FragmentViewMoreCenterBinding>(),
    ViewMoreCenterContract.View, OnCenterImageClickListener {

    private lateinit var viewMoreCenterPresenter: ViewMoreCenterPresenter
    private var progressDialog: SweetAlertDialog? = null

    private val viewMoreCenterAdapter: ViewMoreCenterAdapter by lazy {
        ViewMoreCenterAdapter(this)
    }

    // ----------------------     Base View Binding Fragment     ----------------------

    override fun createBindingFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentViewMoreCenterBinding {
        return FragmentViewMoreCenterBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        viewMoreCenterPresenter = ViewMoreCenterPresenter(
            CenterService.getInstance(CenterRepo.getInstance())
        )
        viewMoreCenterPresenter.setView(this)
    }

    override fun initData() {
        viewMoreCenterAdapter.setData(listCenters)
        binding.rcvCenter.adapter = viewMoreCenterAdapter

        binding.imgBackButton.setOnClickListener {
            goBackFragment()
        }

        var searchValue = ""

        binding.searchCenter.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val nonNullText: String = newText ?: ""
                searchValue = nonNullText
                return true
            }
        })

        binding.btnSearch.setOnClickListener {
            handleClickSearchCenter(searchValue)
        }
    }

    // ----------------------     Handle Search     ----------------------

    private fun handleClickSearchCenter(searchValue: String) {
        viewMoreCenterPresenter.searchCenters(searchValue)
        binding.searchCenter.setQuery("", false)
    }

    override fun onSearchCenters(listCenters: MutableList<Center>) {
        if (listCenters.size == 0) {
            binding.tvNoCenterFound.visibility = View.VISIBLE
            binding.rcvCenter.visibility = View.GONE
        } else {
            viewMoreCenterAdapter.setData(listCenters)
            binding.tvNoCenterFound.visibility = View.GONE
            binding.rcvCenter.visibility = View.VISIBLE
            binding.rcvCenter.adapter = viewMoreCenterAdapter
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

        viewMoreCenterPresenter.getCenterDetail(center.id)
    }

    override fun onImageClick(item: Any) {
        if (item is Center) {
            onCenterImageClick(item)
        }
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

    // ----------------------     Handle Error    ----------------------

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance(listCenters: MutableList<Center>) = ViewMoreCenterFragment(listCenters)
    }
}