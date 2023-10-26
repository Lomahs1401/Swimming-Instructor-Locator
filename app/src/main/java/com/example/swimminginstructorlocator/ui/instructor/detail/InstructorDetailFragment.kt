package com.example.swimminginstructorlocator.ui.instructor.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.swimminginstructorlocator.R
import com.example.swimminginstructorlocator.databinding.FragmentInstructorDetailBinding
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingFragment
import java.lang.Exception

class InstructorDetailFragment : BaseViewBindingFragment<FragmentInstructorDetailBinding>(),
    InstructorDetailContract.View {

    override fun createBindingFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInstructorDetailBinding {
        return FragmentInstructorDetailBinding.inflate(inflater, container, false)
    }

    override fun initData() {
    }

    override fun initView() {
    }

    override fun onSearchInstructor() {
//        TODO("Not yet implemented")
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = InstructorDetailFragment()
    }
}