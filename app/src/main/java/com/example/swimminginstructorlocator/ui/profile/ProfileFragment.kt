package com.example.swimminginstructorlocator.ui.profile

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.swimminginstructorlocator.MainActivity
import com.example.swimminginstructorlocator.data.model.User
import com.example.swimminginstructorlocator.data.repo.AuthRepo
import com.example.swimminginstructorlocator.data.service.AuthService
import com.example.swimminginstructorlocator.databinding.FragmentProfileBinding
import com.example.swimminginstructorlocator.ui.login.LoginActivity
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingFragment
import com.example.swimminginstructorlocator.utils.ext.loadImageWithUrl
import com.example.swimminginstructorlocator.utils.ext.notNull
import java.lang.Exception
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.swimminginstructorlocator.R

class ProfileFragment : BaseViewBindingFragment<FragmentProfileBinding>(),
    ProfileContract.View {
    private lateinit var profilePresenter: ProfilePresenter
    override fun onGetCurrentUser(user: User) {
        binding.etUsername.setText(user.username)
        binding.etEmail.setText(user.email)
        binding.etPhone.setText(user.phone)
        binding.etAddress.setText(user.address)
        binding.etHeight.setText(user.height)
        binding.etWeight.setText(user.weight)
        if (user.gender == 1) {
            binding.etGenre.setText("Male")
            setGenderDrawable(1)
        } else {
            binding.etGenre.setText("Female")
            setGenderDrawable(2)
        }

        if (user.type == 1) {
            binding.tvRole.text = "Instructor"
        } else {
            binding.tvRole.text = "Student"
        }


        user.avatar.notNull {
            user.avatar?.let { it1 -> binding.imgUser.loadImageWithUrl(it1) }
        }
        binding.tvFullname.text = user.username
    }
    private fun setGenderDrawable(gender: Int) {
        val drawableResId = when (gender) {
            1 -> R.drawable.baseline_male_24
            else -> R.drawable.baseline_female_24
        }

        binding.etGenre.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawableResId, 0)
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    override fun createBindingFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater, container, false)
    }

    override fun initData() {
        profilePresenter = ProfilePresenter(
            AuthService.getInstance(AuthRepo.getInstance())
        )
        profilePresenter.setView(this)
        profilePresenter.getCurrentUser()

        binding.btnLogout.setOnClickListener {
            handleClickLogout()
        }
    }

    override fun initView() {
    }

    private fun handleClickLogout() {
        profilePresenter.logout()
    }

    override fun logoutSuccess() {
        SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE).apply {
            titleText = "Logout Successful"
            contentText = "See you again"
            setConfirmClickListener {
                dismiss()
                Intent(context, LoginActivity::class.java).also {
                    startActivity(it)
                    activity?.finish()
                }
            }
            show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}