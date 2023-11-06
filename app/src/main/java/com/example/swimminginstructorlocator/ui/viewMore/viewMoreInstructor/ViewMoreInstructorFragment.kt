package com.example.swimminginstructorlocator.ui.viewMore.viewMoreInstructor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import com.example.swimminginstructorlocator.adapter.ViewMoreInstructorAdapter
import com.example.swimminginstructorlocator.data.model.Center
import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.data.repo.InstructorRepo
import com.example.swimminginstructorlocator.data.service.InstructorService
import com.example.swimminginstructorlocator.databinding.FragmentViewMoreInstructorBinding
import com.example.swimminginstructorlocator.listener.OnItemClickListener
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingFragment
import com.example.swimminginstructorlocator.utils.ext.goBackFragment
import java.lang.Exception

class ViewMoreInstructorFragment : BaseViewBindingFragment<FragmentViewMoreInstructorBinding>(),
    ViewMoreInstructorContract.View, OnItemClickListener {

    private lateinit var viewMoreInstructorPresenter: ViewMoreInstructorPresenter
    private var listInstructors: MutableList<Instructor>? = mutableListOf()

    private val viewMoreInstructorAdapter: ViewMoreInstructorAdapter by lazy {
        ViewMoreInstructorAdapter(this)
    }

    override fun createBindingFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentViewMoreInstructorBinding {
        return FragmentViewMoreInstructorBinding.inflate(inflater, container, false)
    }

    override fun initData() {
        val viewMoreInstructorPresenter = ViewMoreInstructorPresenter(
            InstructorService.getInstance(InstructorRepo.getInstance())
        )
        viewMoreInstructorPresenter.setView(this)
    }

    override fun initView() {
        arguments?.run {
            listInstructors = getParcelableArrayList(LIST_INSTRUCTORS)
        }
        listInstructors?.let { viewMoreInstructorAdapter.setData(it) }
        binding.rcvInstructor.adapter = viewMoreInstructorAdapter

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
        listInstructors?.let { viewMoreInstructorPresenter.searchInstructor(searchValue) }
    }

    override fun onCenterImageClick(center: Center) {
//        TODO("Not yet implemented")
    }

    override fun onInstructorImageClick(instructor: Instructor) {
//        TODO("Not yet implemented")
    }

    override fun onSearchInstructors(listInstructors: MutableList<Instructor>) {
//        TODO("Not yet implemented")
    }

    override fun onError(exception: Exception?) {
//        TODO("Not yet implemented")
    }

    companion object {
        private const val LIST_INSTRUCTORS = "LIST_INSTRUCTORS"

        @JvmStatic
        fun newInstance(listInstructors: MutableList<Instructor>) =
            ViewMoreInstructorFragment().apply {
                arguments = bundleOf(LIST_INSTRUCTORS to listInstructors)
            }
    }
}