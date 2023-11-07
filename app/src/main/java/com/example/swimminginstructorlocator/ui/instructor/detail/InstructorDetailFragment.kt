package com.example.swimminginstructorlocator.ui.instructor.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import com.example.swimminginstructorlocator.R
import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.data.repo.InstructorRepo
import com.example.swimminginstructorlocator.data.service.InstructorService
import com.example.swimminginstructorlocator.databinding.FragmentInstructorDetailBinding
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingFragment
import com.example.swimminginstructorlocator.utils.ext.goBackFragment
import com.example.swimminginstructorlocator.utils.ext.loadImageWithUrl
import com.example.swimminginstructorlocator.utils.ext.notNull
import java.lang.Exception

class InstructorDetailFragment : BaseViewBindingFragment<FragmentInstructorDetailBinding>(),
    InstructorDetailContract.View {

    private lateinit var instructorDetailPresenter: InstructorDetailPresenter
    private var instructorDetail: Instructor? = null

    override fun createBindingFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInstructorDetailBinding {
        return FragmentInstructorDetailBinding.inflate(inflater, container, false)
    }

    override fun initData() {
        instructorDetailPresenter = InstructorDetailPresenter(
            InstructorService.getInstance(InstructorRepo.getInstance())
        )
        instructorDetailPresenter.setView(this)
    }

    override fun initView() {
        arguments?.run {
            instructorDetail = getParcelable(INSTRUCTOR_DETAIL)
        }

        binding.imgBackButton.setOnClickListener {
            goBackFragment()
        }

        instructorDetail?.image.notNull {
            instructorDetail?.image?.let {
                binding.imgInstructor.loadImageWithUrl(instructorDetail!!.image)
            }
        }

    }

    override fun onSearchInstructor() {
//        TODO("Not yet implemented")
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val INSTRUCTOR_DETAIL = "INSTRUCTOR_DETAIL"

        @JvmStatic
        fun newInstance(instructorDetail: Instructor) = InstructorDetailFragment().apply {
            arguments = bundleOf(INSTRUCTOR_DETAIL to instructorDetail)
        }
    }
}