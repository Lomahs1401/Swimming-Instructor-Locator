package com.example.swimminginstructorlocator.ui.login

import android.content.Intent
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.swimminginstructorlocator.MainActivity
import com.example.swimminginstructorlocator.data.model.User
import com.example.swimminginstructorlocator.data.repo.AuthRepo
import com.example.swimminginstructorlocator.data.request.LoginRequest
import com.example.swimminginstructorlocator.data.service.AuthService
import com.example.swimminginstructorlocator.databinding.ActivityLoginBinding
import com.example.swimminginstructorlocator.ui.register.RegisterActivity
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingActivity

class LoginActivity : BaseViewBindingActivity<ActivityLoginBinding>(), LoginContract.View {

    private lateinit var loginPresenter: LoginPresenter
    private var progressDialog: SweetAlertDialog? = null

    override fun createBindingActivity(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun initView() {
        loginPresenter = LoginPresenter(
            AuthService.getInstance(AuthRepo.getInstance())
        )
        loginPresenter.setView(this)
    }

    override fun initData() {
        binding.btnLogin.setOnClickListener {
            handleClickSignIn()
        }
        binding.tvSignUpLink.setOnClickListener {
            Intent(this, RegisterActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun handleClickSignIn() {
        val email = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()

        val loginRequest = LoginRequest(email, password)

        val isValidLoginForm =
            loginPresenter.validateLoginForm(loginRequest, binding, applicationContext)

        if (isValidLoginForm) {
            progressDialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("Loading")
                .setContentText("Please wait...")
                .apply {
                    setCancelable(false)
                    show()
                }

            loginPresenter.signIn(loginRequest)
        }
    }

    override fun onSignIn(user: User) {
        progressDialog?.dismissWithAnimation()
        Intent(this@LoginActivity, MainActivity::class.java).also {
            startActivity(it)
        }
    }

    override fun onError(exception: Exception?) {
        progressDialog?.dismissWithAnimation()

        SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE).apply {
            titleText = "Login Failed"
            contentText = exception?.message
            show()
        }
    }
}