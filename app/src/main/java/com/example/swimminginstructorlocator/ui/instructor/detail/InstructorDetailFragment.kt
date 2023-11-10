package com.example.swimminginstructorlocator.ui.instructor.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.swimminginstructorlocator.data.model.InstructorDetail
import com.example.swimminginstructorlocator.data.repo.InstructorRepo
import com.example.swimminginstructorlocator.data.service.InstructorService
import com.example.swimminginstructorlocator.databinding.FragmentInstructorDetailBinding
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingFragment
import com.example.swimminginstructorlocator.utils.ext.goBackFragment
import com.example.swimminginstructorlocator.utils.ext.loadImageWithUrl
import com.example.swimminginstructorlocator.utils.ext.notNull
import java.lang.Exception

class InstructorDetailFragment(
    private val id: String,
) : BaseViewBindingFragment<FragmentInstructorDetailBinding>(),
    InstructorDetailContract.View {

    private lateinit var instructorDetailPresenter: InstructorDetailPresenter
    private var instructorId: String? = null

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
        instructorDetailPresenter.getInstructorDetail(id)
    }

    override fun initView() {
        binding.imgBackButton.setOnClickListener {
            goBackFragment()
        }
    }

    override fun onGetInstructorDetail(instructorDetail: InstructorDetail) {
        instructorDetail.image.notNull {
            binding.imgInstructor.loadImageWithUrl(instructorDetail.image)
        }

        if (instructorDetail.gender == 1) {
            binding.tvInstructorGender.text = "Male"
        } else {
            binding.tvInstructorGender.text = "Female"
        }
        binding.tvInstructorName.text = instructorDetail.instructorName
        binding.tvInstructorAge.text = instructorDetail.age.toString()
        binding.tvInstructorDescription.text = instructorDetail.description
        binding.tvInstructorGraduate.text = instructorDetail.graduate
        binding.tvInstructorCertification.text = instructorDetail.certificate
        binding.tvInstructorExperience.text = instructorDetail.yearExperiences.toString()
        binding.tvInstructorEmail.text = instructorDetail.email
        binding.tvInstructorPhone.text = instructorDetail.phone
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance(id: String) = InstructorDetailFragment(id)
    }
}