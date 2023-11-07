package com.example.swimminginstructorlocator.ui.center.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.swimminginstructorlocator.data.model.Center
import com.example.swimminginstructorlocator.data.repo.CenterRepo
import com.example.swimminginstructorlocator.data.service.CenterService
import com.example.swimminginstructorlocator.databinding.FragmentCenterDetailBinding
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingFragment
import com.example.swimminginstructorlocator.utils.ext.goBackFragment
import com.example.swimminginstructorlocator.utils.ext.loadImageWithUrl
import com.example.swimminginstructorlocator.utils.ext.notNull
import java.lang.Exception

class CenterDetailFragment : BaseViewBindingFragment<FragmentCenterDetailBinding>(), CenterDetailContract.View {

    private lateinit var centerDetailPresenter: CenterDetailPresenter
    private var centerDetail: Center? = null

    override fun createBindingFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCenterDetailBinding {
        return FragmentCenterDetailBinding.inflate(inflater, container, false)
    }

    override fun initData() {
        centerDetailPresenter = CenterDetailPresenter(
            CenterService.getInstance(CenterRepo.getInstance())
        )
        centerDetailPresenter.setView(this)
    }

    override fun initView() {
        arguments?.run {
            centerDetail = getParcelable(CENTER_DETAIL)
        }

        binding.imgBackButton.setOnClickListener {
            goBackFragment()
        }

        centerDetail?.image.notNull {
            centerDetail?.image?.let { image -> binding.imgCenter.loadImageWithUrl(image) }
        }
        binding.tvCenterName.text = centerDetail?.centerName
        binding.tvCenterDescription.text = centerDetail?.description
        binding.tvCenterPhone.text = centerDetail?.phone
        binding.tvCenterAddress.text = centerDetail?.address
        binding.tvCenterEmail.text = centerDetail?.email
    }

    override fun onError(exception: Exception?) {
//        TODO("Not yet implemented")
    }

    companion object {
        private const val CENTER_DETAIL = "CENTER_DETAIL"

        @JvmStatic
        fun newInstance(centerDetail: Center) = CenterDetailFragment().apply {
            arguments = bundleOf(CENTER_DETAIL to centerDetail)
        }
    }
}