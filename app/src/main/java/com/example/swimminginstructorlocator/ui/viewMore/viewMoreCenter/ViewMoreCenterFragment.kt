package com.example.swimminginstructorlocator.ui.viewMore.viewMoreCenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import com.example.swimminginstructorlocator.R
import com.example.swimminginstructorlocator.adapter.ViewMoreCenterAdapter
import com.example.swimminginstructorlocator.adapter.ViewMoreInstructorAdapter
import com.example.swimminginstructorlocator.data.model.Center
import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.data.repo.CenterRepo
import com.example.swimminginstructorlocator.data.repo.InstructorRepo
import com.example.swimminginstructorlocator.data.service.CenterService
import com.example.swimminginstructorlocator.data.service.InstructorService
import com.example.swimminginstructorlocator.databinding.FragmentViewMoreCenterBinding
import com.example.swimminginstructorlocator.databinding.FragmentViewMoreInstructorBinding
import com.example.swimminginstructorlocator.listener.OnItemClickListener
import com.example.swimminginstructorlocator.ui.viewMore.viewMoreInstructor.ViewMoreInstructorFragment
import com.example.swimminginstructorlocator.ui.viewMore.viewMoreInstructor.ViewMoreInstructorPresenter
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingFragment
import com.example.swimminginstructorlocator.utils.ext.goBackFragment
import java.lang.Exception

class ViewMoreCenterFragment : BaseViewBindingFragment<FragmentViewMoreCenterBinding>(),
    ViewMoreCenterContract.View, OnItemClickListener {

    private lateinit var viewMoreCenterPresenter: ViewMoreCenterPresenter
    private var listCenters: MutableList<Center>? = mutableListOf()

    private val viewMoreCenterAdapter: ViewMoreCenterAdapter by lazy {
        ViewMoreCenterAdapter(this)
    }

    override fun createBindingFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentViewMoreCenterBinding {
        return FragmentViewMoreCenterBinding.inflate(inflater, container, false)
    }

    override fun initData() {
        val viewMoreCenterPresenter = ViewMoreCenterPresenter(
            CenterService.getInstance(CenterRepo.getInstance())
        )
        viewMoreCenterPresenter.setView(this)
    }

    override fun initView() {
        arguments?.run {
            listCenters = getParcelableArrayList(LIST_CENTERS)
        }
        listCenters?.let { viewMoreCenterAdapter.setData(it) }
        binding.rcvInstructor.adapter = viewMoreCenterAdapter

        binding.imgBackButton.setOnClickListener {
            goBackFragment()
        }

        var searchValue = ""

        binding.searchInstructor.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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
            handleClickSearchInstructor(searchValue)
        }
    }

    private fun handleClickSearchInstructor(searchValue: String) {
        listCenters?.let { viewMoreCenterPresenter.searchCenter(searchValue) }
    }

    override fun onCenterImageClick(center: Center) {
//        TODO("Not yet implemented")
    }

    override fun onInstructorImageClick(instructor: Instructor) {
//        TODO("Not yet implemented")
    }

    override fun onSearchCenter(listCenters: MutableList<Center>) {
//        TODO("Not yet implemented")
    }

    override fun onError(exception: Exception?) {
//        TODO("Not yet implemented")
    }

    companion object {
        private const val LIST_CENTERS = "LIST_CENTERS"

        @JvmStatic
        fun newInstance(listCenters: MutableList<Center>) =
            ViewMoreCenterFragment().apply {
                arguments = bundleOf(LIST_CENTERS to listCenters)
            }
    }
}