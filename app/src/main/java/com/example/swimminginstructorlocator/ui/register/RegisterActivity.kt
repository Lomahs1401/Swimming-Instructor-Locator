package com.example.swimminginstructorlocator.ui.register

import android.content.Intent
import android.widget.Toast
import com.example.swimminginstructorlocator.data.repo.AuthRepo
import com.example.swimminginstructorlocator.data.request.RegisterRequest
import com.example.swimminginstructorlocator.data.service.AuthService
import com.example.swimminginstructorlocator.databinding.ActivityRegisterBinding
import com.example.swimminginstructorlocator.ui.login.LoginActivity
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingActivity
import java.lang.Exception

class RegisterActivity : BaseViewBindingActivity<ActivityRegisterBinding>(), RegisterContract.View {

    private lateinit var registerPresenter: RegisterPresenter

    override fun createBindingActivity(): ActivityRegisterBinding {
        return ActivityRegisterBinding.inflate(layoutInflater)
    }

    override fun initView() {
        registerPresenter = RegisterPresenter(
            AuthService.getInstance(AuthRepo.getInstance())
        )
        registerPresenter.setView(this)
    }

    override fun initData() {
        binding.btnSignup.setOnClickListener {
            handleClickSignUp()
        }
        binding.tvSignInLink.setOnClickListener {
            Intent(this, LoginActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun handleClickSignUp() {
        val username = binding.etUsername.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        val registerRequest = RegisterRequest(username, email, password)

        val isValidRegisterForm =
            registerPresenter.validateRegisterForm(registerRequest, binding, applicationContext)

        if (isValidRegisterForm) {
            registerPresenter.signUp(registerRequest)
        }
    }

    override fun onSignUp() {
        Toast.makeText(this, "OKE", Toast.LENGTH_SHORT).show()
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(this, exception?.message, Toast.LENGTH_SHORT).show()
    }
}