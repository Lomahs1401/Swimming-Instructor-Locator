package com.example.swimminginstructorlocator.ui.register

import android.content.Context
import com.example.swimminginstructorlocator.data.request.RegisterRequest
import com.example.swimminginstructorlocator.databinding.ActivityRegisterBinding
import com.example.swimminginstructorlocator.utils.base.BasePresenter
import java.lang.Exception

class RegisterContract {
    /**
     * View
     */
    interface View {
        fun onSignUp()
        fun onError(exception: Exception?)
    }

    /**
     * Presenter
     */
    interface Presenter : BasePresenter<View> {
        fun validateRegisterForm(
            registerRequest: RegisterRequest,
            binding: ActivityRegisterBinding,
            context: Context
        ) : Boolean

        fun signUp(registerRequest: RegisterRequest)
    }
}