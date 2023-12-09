package com.example.swimminginstructorlocator.ui.login

import android.content.Context
import android.view.View
import com.example.swimminginstructorlocator.R
import com.example.swimminginstructorlocator.data.model.User
import com.example.swimminginstructorlocator.data.request.LoginRequest
import com.example.swimminginstructorlocator.data.service.impl.AuthServiceImpl
import com.example.swimminginstructorlocator.databinding.ActivityLoginBinding
import com.example.swimminginstructorlocator.listener.OnResultListener
import java.lang.Exception

class LoginPresenter(
    private val authService: AuthServiceImpl
) : LoginContract.Presenter {

    private var view: LoginContract.View? = null
    override fun onStart() {
//        TODO("Not yet implemented")
    }

    override fun onStop() {
//        TODO("Not yet implemented")
    }

    override fun setView(view: LoginContract.View?) {
        this.view = view
    }

    override fun validateLoginForm(
        loginRequest: LoginRequest,
        binding: ActivityLoginBinding,
        context: Context
    ): Boolean {
        val isValidEmail = validateEmail(loginRequest.email, binding, context)
        val isValidPassword = validatePassword(loginRequest.password, binding, context)
        return isValidEmail && isValidPassword
    }

    override fun signIn(loginRegister: LoginRequest) {
        authService.login(loginRegister, object : OnResultListener<User> {
            override fun onSuccess(dataResult: User) {
                view?.onSignIn(dataResult)
            }

            override fun onError(exception: Exception?) {
                view?.onError(exception)
            }
        })
    }

    private fun validateEmail(
        email: String, binding: ActivityLoginBinding, context: Context
    ): Boolean {
        // Email is required
        if (email.isBlank()) {
            binding.tvErrorEmail.visibility = View.VISIBLE
            binding.tvErrorEmail.text = context.getString(R.string.validate_email_blank)
            return false
        }

        // Email is invalid
        if (isInvalidEmail(email)) {
            binding.tvErrorEmail.visibility = View.VISIBLE
            binding.tvErrorEmail.text = context.getString(R.string.validate_email_invalid)
            return false
        }

        // Clear any previous error messages if all validations pass
        binding.tvErrorEmail.visibility = View.INVISIBLE
        binding.tvErrorEmail.text = ""

        // All validations pass
        return true
    }

    private fun validatePassword(
        password: String, binding: ActivityLoginBinding, context: Context
    ): Boolean {
        // Password is required
        if (password.isBlank()) {
            binding.tvErrorPassword.visibility = View.VISIBLE
            binding.tvErrorPassword.text = context.getString(R.string.validate_password_blank)
            return false
        }

        // Clear any previous error messages if all validations pass
        binding.tvErrorPassword.visibility = View.INVISIBLE
        binding.tvErrorPassword.text = ""

        // All validations pass
        return true
    }

    private fun isInvalidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return !email.matches(emailRegex.toRegex())
    }
}