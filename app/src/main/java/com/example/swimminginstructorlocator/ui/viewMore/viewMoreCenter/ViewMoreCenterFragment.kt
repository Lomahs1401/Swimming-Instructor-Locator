package com.example.swimminginstructorlocator.ui.viewMore.viewMoreCenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import com.example.swimminginstructorlocator.adapter.ViewMoreCenterAdapter
import com.example.swimminginstructorlocator.data.model.Center
import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.data.repo.CenterRepo
import com.example.swimminginstructorlocator.data.service.CenterService
import com.example.swimminginstructorlocator.databinding.FragmentViewMoreCenterBinding
import com.example.swimminginstructorlocator.listener.OnItemClickListener
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
        viewMoreCenterPresenter = ViewMoreCenterPresenter(
            CenterService.getInstance(CenterRepo.getInstance())
        )
        viewMoreCenterPresenter.setView(this)
    }

    override fun initView() {
        arguments?.run {
            listCenters = getParcelableArrayList(LIST_CENTERS)
        }
        listCenters?.let { viewMoreCenterAdapter.setData(it) }
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

    private fun handleClickSearchCenter(searchValue: String) {
        listCenters?.let { viewMoreCenterPresenter.searchCenters(searchValue) }
        binding.searchCenter.setQuery("", false)
    }

    override fun onCenterImageClick(center: Center) {
//        TODO("Not yet implemented")
    }

    override fun onInstructorImageClick(instructor: Instructor) {
//        TODO("Not yet implemented")
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

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
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