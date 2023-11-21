package com.example.swimminginstructorlocator.ui.register

import android.content.Context
import android.view.View
import com.example.swimminginstructorlocator.R
import com.example.swimminginstructorlocator.data.model.User
import com.example.swimminginstructorlocator.data.request.RegisterRequest
import com.example.swimminginstructorlocator.data.service.impl.AuthServiceImpl
import com.example.swimminginstructorlocator.databinding.ActivityRegisterBinding
import com.example.swimminginstructorlocator.listener.OnResultListener
import java.lang.Exception

class RegisterPresenter(
    private val authService: AuthServiceImpl
) : RegisterContract.Presenter {

    private var view: RegisterContract.View? = null

    override fun onStart() {
//        TODO("Not yet implemented")
    }

    override fun onStop() {
//        TODO("Not yet implemented")
    }

    override fun setView(view: RegisterContract.View?) {
        this.view = view
    }

    override fun validateRegisterForm(
        registerRequest: RegisterRequest, binding: ActivityRegisterBinding, context: Context
    ): Boolean {
        val isValidUsername = validateUsername(registerRequest.username, binding, context)
        val isValidEmail = validateEmail(registerRequest.email, binding, context)
        val isValidPassword = validatePassword(registerRequest.password, binding, context)

        return isValidUsername && isValidEmail && isValidPassword
    }

    override fun signUp(registerRequest: RegisterRequest) {
        authService.register(registerRequest, object : OnResultListener<User> {
            override fun onSuccess(dataResult: User) {
                view?.onSignUp()
            }

            override fun onError(exception: Exception?) {
                view?.onError(exception)
            }
        })
    }

    private fun validateUsername(
        username: String, binding: ActivityRegisterBinding, context: Context
    ): Boolean {
        // Username is required
        if (username.isBlank()) {
            binding.tvErrorUsername.visibility = View.VISIBLE
            binding.tvErrorUsername.text = context.getString(R.string.validate_username_blank)
            return false
        }

        // Username must be between 6 and 18 characters
        if (isInvalidUsernameLength(username)) {
            binding.tvErrorUsername.visibility = View.VISIBLE
            binding.tvErrorUsername.text = context.getString(R.string.validate_username_length)
            return false
        }

        // The first character cannot be a numeric character
        if (startsWithNumeric(username)) {
            binding.tvErrorUsername.visibility = View.VISIBLE
            binding.tvErrorUsername.text = context.getString(R.string.validate_username_digit)
            return false
        }

        // Clear any previous error messages if all validations pass
        binding.tvErrorUsername.visibility = View.INVISIBLE
        binding.tvErrorUsername.text = ""

        // All validations pass
        return true
    }

    private fun validateEmail(
        email: String, binding: ActivityRegisterBinding, context: Context
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
        password: String, binding: ActivityRegisterBinding, context: Context
    ): Boolean {
        // Password is required
        if (password.isBlank()) {
            binding.tvErrorPassword.visibility = View.VISIBLE
            binding.tvErrorPassword.text = context.getString(R.string.validate_password_blank)
            return false
        }

        // Password must be between 8 and 16 characters
        if (isInvalidPasswordLength(password)) {
            binding.tvErrorPassword.visibility = View.VISIBLE
            binding.tvErrorPassword.text = context.getString(R.string.validate_password_length)
            return false
        }

        // Password must contain 1 uppercase character, 1 alphanumeric character, and 1 special character
        if (isInvalidPasswordFormat(password)) {
            binding.tvErrorPassword.visibility = View.VISIBLE
            binding.tvErrorPassword.text = context.getString(R.string.validate_password_format)
            return false
        }

        // Clear any previous error messages if all validations pass
        binding.tvErrorPassword.visibility = View.INVISIBLE
        binding.tvErrorPassword.text = ""

        // All validations pass
        return true
    }

    private fun isInvalidUsernameLength(username: String): Boolean {
        return username.length !in 6..18
    }

    private fun isInvalidPasswordLength(password: String): Boolean {
        return password.length !in 8..16
    }

    private fun startsWithNumeric(username: String): Boolean {
        return username.isNotEmpty() && username[0].isDigit()
    }

    private fun isInvalidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return !email.matches(emailRegex.toRegex())
    }

    private fun isInvalidPasswordFormat(password: String): Boolean {
        val passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]+$"
        return !password.matches(passwordRegex.toRegex())
    }
}