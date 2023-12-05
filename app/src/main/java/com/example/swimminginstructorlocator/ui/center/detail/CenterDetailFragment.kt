package com.example.swimminginstructorlocator.ui.center.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.swimminginstructorlocator.adapter.InstructorAdapter
import com.example.swimminginstructorlocator.data.model.CenterDetail
import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.data.repo.CenterRepo
import com.example.swimminginstructorlocator.data.service.CenterService
import com.example.swimminginstructorlocator.databinding.FragmentCenterDetailBinding
import com.example.swimminginstructorlocator.listener.OnInstructorImageClickListener
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingFragment
import com.example.swimminginstructorlocator.utils.ext.goBackFragment
import com.example.swimminginstructorlocator.utils.ext.loadImageWithUrl
import com.example.swimminginstructorlocator.utils.ext.notNull
import java.lang.Exception

class CenterDetailFragment : BaseViewBindingFragment<FragmentCenterDetailBinding>(),
    CenterDetailContract.View, OnInstructorImageClickListener {

    private lateinit var centerDetailPresenter: CenterDetailPresenter
    private val instructorAdapter: InstructorAdapter by lazy {
        InstructorAdapter(itemClickListener = this)
    }

    override fun createBindingFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCenterDetailBinding {
        return FragmentCenterDetailBinding.inflate(inflater, container, false)
    }

    override fun initView() {
        centerDetailPresenter = CenterDetailPresenter(
            CenterService.getInstance(CenterRepo.getInstance())
        )
        centerDetailPresenter.setView(this)
    }

    override fun initData() {
        binding.tvCenterName.text = centerDetail?.centerName
        binding.tvCenterDescription.text = centerDetail?.description
        binding.tvCenterPhone.text = centerDetail?.phone
        binding.tvCenterAddress.text = centerDetail?.address
        binding.tvCenterEmail.text = centerDetail?.email

        binding.imgBackButton.setOnClickListener {
            goBackFragment()
        }

        centerDetail?.image.notNull {
            centerDetail?.image.let { image ->
                image?.let { value ->
                    binding.imgCenter.loadImageWithUrl(
                        value
                    )
                }
            }
        }

        if (centerDetail?.instructors?.size == 0) {
            binding.rcvInstructor.visibility = View.INVISIBLE
            binding.tvNoInstructorFound.visibility = View.VISIBLE
        } else {
            centerDetail?.instructors?.let { instructorAdapter.setData(it) }
            binding.rcvInstructor.adapter = instructorAdapter
            binding.rcvInstructor.visibility = View.VISIBLE
            binding.tvNoInstructorFound.visibility = View.INVISIBLE
        }
    }

    override fun onInstructorImageClick(instructor: Instructor) {
//        TODO("Not yet implemented")
    }

    override fun onImageClick(item: Any) {
//        TODO("Not yet implemented")
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private var centerDetail: CenterDetail? = null

        @JvmStatic
        fun newInstance() = CenterDetailFragment()

        @JvmStatic
        fun setCenterDetail(centers: CenterDetail) {
            this.centerDetail = centers
        }
    }
}