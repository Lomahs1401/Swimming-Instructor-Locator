package com.example.swimminginstructorlocator.ui.teacher.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.swimminginstructorlocator.databinding.FragmentSearchTeacherBinding
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingFragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SearchTeacherFragment : BaseViewBindingFragment<FragmentSearchTeacherBinding>() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun createBindingFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchTeacherBinding {
        return FragmentSearchTeacherBinding.inflate(inflater, container, false)
    }

    override fun initData() {
    }

    override fun initView() {
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchTeacherFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}