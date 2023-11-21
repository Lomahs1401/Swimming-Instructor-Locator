package com.example.swimminginstructorlocator.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.swimminginstructorlocator.R
import android.widget.Toast
import com.example.swimminginstructorlocator.data.model.User
import com.example.swimminginstructorlocator.data.repo.AuthRepo
import com.example.swimminginstructorlocator.data.request.LoginRequest
import com.example.swimminginstructorlocator.data.request.RegisterRequest
import com.example.swimminginstructorlocator.databinding.ActivityLoginBinding
import com.example.swimminginstructorlocator.data.service.AuthService
import com.example.swimminginstructorlocator.listener.OnResultListener
import com.example.swimminginstructorlocator.ui.register.RegisterPresenter
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
    }

    private fun handleClickSignIn() {
        val email = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()

        val loginRequest = LoginRequest(email, password)

        val isValidLoginForm = loginPresenter.validateLoginForm(loginRequest, binding, applicationContext)

        if (isValidLoginForm) {
            loginPresenter.signIn(loginRequest)
        }
    }

    override fun onSignIn() {
        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(this, exception?.message, Toast.LENGTH_SHORT).show()
    }
}