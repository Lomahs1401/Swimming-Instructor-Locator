package com.example.swimminginstructorlocator.ui.viewMore.viewMoreInstructor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.swimminginstructorlocator.R
import com.example.swimminginstructorlocator.adapter.ViewMoreInstructorAdapter
import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.data.model.InstructorDetail
import com.example.swimminginstructorlocator.data.repo.InstructorRepo
import com.example.swimminginstructorlocator.data.service.InstructorService
import com.example.swimminginstructorlocator.databinding.FragmentViewMoreInstructorBinding
import com.example.swimminginstructorlocator.listener.OnInstructorImageClickListener
import com.example.swimminginstructorlocator.ui.instructor.detail.InstructorDetailFragment
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingFragment
import com.example.swimminginstructorlocator.utils.ext.addFragment
import com.example.swimminginstructorlocator.utils.ext.goBackFragment
import java.lang.Exception

class ViewMoreInstructorFragment : BaseViewBindingFragment<FragmentViewMoreInstructorBinding>(),
    ViewMoreInstructorContract.View, OnInstructorImageClickListener {

    private lateinit var viewMoreInstructorPresenter: ViewMoreInstructorPresenter
    private var progressDialog: SweetAlertDialog? = null

    private val viewMoreInstructorAdapter: ViewMoreInstructorAdapter by lazy {
        ViewMoreInstructorAdapter(this)
    }

    // ----------------------     Base View Binding Fragment     ----------------------

    override fun createBindingFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentViewMoreInstructorBinding {
        return FragmentViewMoreInstructorBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        viewMoreInstructorPresenter = ViewMoreInstructorPresenter(
            InstructorService.getInstance(InstructorRepo.getInstance())
        )
        viewMoreInstructorPresenter.setView(this)
    }

    override fun initData() {
        viewMoreInstructorAdapter.setData(listInstructors)
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

    // ----------------------     Handle Search     ----------------------

    private fun handleClickSearchInstructor(searchValue: String) {
        viewMoreInstructorPresenter.searchInstructor(searchValue)
        binding.searchInstructor.setQuery("", false)
    }

    override fun onSearchInstructors(listInstructors: MutableList<Instructor>) {
        if (listInstructors.size == 0) {
            binding.tvNoInstructorFound.visibility = View.VISIBLE
            binding.rcvInstructor.visibility = View.GONE
        } else {
            viewMoreInstructorAdapter.setData(listInstructors)
            binding.tvNoInstructorFound.visibility = View.GONE
            binding.rcvInstructor.visibility = View.VISIBLE
            binding.rcvInstructor.adapter = viewMoreInstructorAdapter
        }
    }

    // ----------------------     On Item Click Listener     ----------------------

    override fun onInstructorImageClick(instructor: Instructor) {
        progressDialog = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
            .setTitleText("Loading...")
            .apply {
                setCancelable(false)
                show()
            }

        viewMoreInstructorPresenter.getInstructorDetail(instructor.id)
    }

    override fun onImageClick(item: Any) {
        if (item is Instructor) {
            onInstructorImageClick(item)
        }
    }

    override fun onGetInstructorDetail(instructorDetail: InstructorDetail) {
        progressDialog?.dismissWithAnimation()

        InstructorDetailFragment.setInstructorDetail(instructorDetail)
        val instructorDetailFragment = InstructorDetailFragment.newInstance()
        addFragment(
            R.id.fragment_home_container,
            instructorDetailFragment,
            addToBackStack = true
        )
    }

    // ----------------------     Handle Error    ----------------------

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private var listInstructors: MutableList<Instructor> = mutableListOf()

        @JvmStatic
        fun newInstance() = ViewMoreInstructorFragment()

        @JvmStatic
        fun setListInstructors(instructors: MutableList<Instructor>) {
            this.listInstructors = instructors
        }
    }
}