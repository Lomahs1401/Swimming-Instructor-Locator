package com.example.swimminginstructorlocator.ui.register

import android.content.Intent
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.swimminginstructorlocator.data.repo.AuthRepo
import com.example.swimminginstructorlocator.data.request.RegisterRequest
import com.example.swimminginstructorlocator.data.service.AuthService
import com.example.swimminginstructorlocator.databinding.ActivityRegisterBinding
import com.example.swimminginstructorlocator.ui.login.LoginActivity
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingActivity
import java.lang.Exception

class RegisterActivity : BaseViewBindingActivity<ActivityRegisterBinding>(), RegisterContract.View {

    private lateinit var registerPresenter: RegisterPresenter

    // ----------------------     Base View Binding Activity     ----------------------

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

    // ----------------------     Handle Register     ----------------------

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
        SweetAlertDialog(applicationContext, SweetAlertDialog.SUCCESS_TYPE).apply {
            setTitleText("Register Successful")
            .setConfirmClickListener {
                Intent(this@RegisterActivity, LoginActivity::class.java).also {
                    startActivity(intent)
                }
                finish()
            }
            setCancelable(false)
            show()
        }
    }

    // ----------------------     Handle Error     ----------------------

    override fun onError(exception: Exception?) {
        SweetAlertDialog(applicationContext, SweetAlertDialog.SUCCESS_TYPE).apply {
            titleText = exception?.message
            setCancelable(true)
            show()
        }
    }
}