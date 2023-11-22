package com.example.swimminginstructorlocator.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.swimminginstructorlocator.data.model.User
import com.example.swimminginstructorlocator.data.repo.AuthRepo
import com.example.swimminginstructorlocator.data.service.AuthService
import com.example.swimminginstructorlocator.databinding.FragmentProfileBinding
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingFragment
import com.example.swimminginstructorlocator.utils.ext.loadImageWithUrl
import com.example.swimminginstructorlocator.utils.ext.notNull
import java.lang.Exception

class ProfileFragment : BaseViewBindingFragment<FragmentProfileBinding>(),
ProfileContract.View{
    private lateinit var profilePresenter: ProfilePresenter
    override fun onGetCurrentUser(user: User) {
        binding.etUsername.setText(user.username)
        binding.etEmail.setText(user.email)
        binding.etPhone.setText(user.phone)

        user.avatar.notNull {
            user.avatar?.let { it1 -> binding.imgUser.loadImageWithUrl(it1) }
        }
    }

    override fun onError(exception: Exception?) {
        TODO("Not yet implemented")
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    override fun createBindingFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileBinding {
        TODO("Not yet implemented")
        return FragmentProfileBinding.inflate(inflater, container, false)
    }

    override fun initData() {
        TODO("Not yet implemented")
        profilePresenter = ProfilePresenter(
            AuthService.getInstance(AuthRepo.getInstance())
        )
        profilePresenter.setView(this)
        profilePresenter.getCurrentUser()
    }

    override fun initView() {
        TODO("Not yet implemented")
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}