package com.example.swimminginstructorlocator.ui.login

import android.content.Context
import com.example.swimminginstructorlocator.data.model.User
import com.example.swimminginstructorlocator.data.request.LoginRequest
import com.example.swimminginstructorlocator.databinding.ActivityLoginBinding
import com.example.swimminginstructorlocator.utils.base.BasePresenter
import java.lang.Exception

class LoginContract {
    /**
     * View
     */
    interface View {
        fun onSignIn(user: User)
        fun onError(exception: Exception?)
    }

    /**
     * Presenter
     */
    interface Presenter : BasePresenter<View> {
        fun validateLoginForm(
            loginRequest: LoginRequest,
            binding: ActivityLoginBinding,
            context: Context
        ) : Boolean
        fun signIn(loginRegister: LoginRequest)
    }
}