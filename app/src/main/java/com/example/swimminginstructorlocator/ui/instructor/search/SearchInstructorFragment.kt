package com.example.swimminginstructorlocator.ui.instructor.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.swimminginstructorlocator.databinding.FragmentSearchInstructorBinding
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingFragment
import java.lang.Exception

class SearchInstructorFragment : BaseViewBindingFragment<FragmentSearchInstructorBinding>(), SearchInstructorContract.View {

    override fun createBindingFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchInstructorBinding {
        return FragmentSearchInstructorBinding.inflate(inflater, container, false)
    }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun onSearchInstructor() {
//        TODO("Not yet implemented")
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchInstructorFragment()
    }
}