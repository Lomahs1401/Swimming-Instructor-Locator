package com.example.swimminginstructorlocator.ui.login

import android.content.Intent
import android.util.Log
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.swimminginstructorlocator.MainActivity
import com.example.swimminginstructorlocator.R
import com.example.swimminginstructorlocator.data.model.User
import com.example.swimminginstructorlocator.data.repo.AuthRepo
import com.example.swimminginstructorlocator.data.request.LoginRequest
import com.example.swimminginstructorlocator.data.service.AuthService
import com.example.swimminginstructorlocator.databinding.ActivityLoginBinding
import com.example.swimminginstructorlocator.ui.register.RegisterActivity
import com.example.swimminginstructorlocator.utils.DataLocalManager
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
        binding.btnFacebook.setOnClickListener {
            handleClickSignInWithSocialMedia()
        }
        binding.btnGoogle.setOnClickListener {
            handleClickSignInWithSocialMedia()
        }
        binding.btnTwitter.setOnClickListener {
            handleClickSignInWithSocialMedia()
        }
        binding.tvSignUpLink.setOnClickListener {
            Intent(this, RegisterActivity::class.java).also {
                startActivity(it)
            }
        }
        binding.tvBackToHomepage.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun handleClickSignInWithSocialMedia() {
        SweetAlertDialog(this@LoginActivity, SweetAlertDialog.NORMAL_TYPE).apply {
            setTitleText(R.string.update_soon)
            setCancelable(true)
            show()
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
                .setTitleText(R.string.loading)
                .setContentText(getString(R.string.please_wait))
                .apply {
                    setCancelable(false)
                    show()
                }

            loginPresenter.signIn(loginRequest)
        }
    }

    override fun onSignIn(user: User) {
        progressDialog?.dismissWithAnimation()

        DataLocalManager.saveUser(user)
        DataLocalManager.setIsLoggedIn(true)

        Intent(this@LoginActivity, MainActivity::class.java).also {
            startActivity(it)
        }
    }

    override fun onError(exception: Exception?) {
        progressDialog?.dismissWithAnimation()

        SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE).apply {
            titleText = getString(R.string.login_failed)
            contentText = getString(R.string.try_again)
            show()
        }

        exception?.message?.let { Log.e("ERROR", it) }
    }
}