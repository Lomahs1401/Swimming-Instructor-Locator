package com.example.swimminginstructorlocator.ui.login

import android.content.Intent
import android.widget.Toast
import com.example.swimminginstructorlocator.MainActivity
import com.example.swimminginstructorlocator.data.repo.AuthRepo
import com.example.swimminginstructorlocator.data.request.LoginRequest
import com.example.swimminginstructorlocator.databinding.ActivityLoginBinding
import com.example.swimminginstructorlocator.data.service.AuthService
import com.example.swimminginstructorlocator.ui.register.RegisterActivity
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingActivity
import java.lang.Exception

class LoginActivity : BaseViewBindingActivity<ActivityLoginBinding>(), LoginContract.View {

    private lateinit var loginPresenter: LoginPresenter

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
            loginPresenter.signIn(loginRequest)
        }
    }

    override fun onSignIn() {
        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
        Intent(this, MainActivity::class.java).also {
            startActivity(it)
        }
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(this, exception?.message, Toast.LENGTH_SHORT).show()
    }
}