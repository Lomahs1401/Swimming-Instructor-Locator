package com.example.swimminginstructorlocator.ui.teacher.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swimminginstructorlocator.R
import com.example.swimminginstructorlocator.databinding.FragmentTeacherDetailBinding
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingFragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TeacherDetailFragment : BaseViewBindingFragment<FragmentTeacherDetailBinding>() {
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
    ): FragmentTeacherDetailBinding {
        return FragmentTeacherDetailBinding.inflate(inflater, container, false)
    }

    override fun initData() {
    }

    override fun initView() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teacher_detail, container, false)
    }

    companion object {
        @JvmStatic fun newInstance(param1: String, param2: String) =
                TeacherDetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}