package com.example.swimminginstructorlocator.data.service

import com.example.swimminginstructorlocator.data.model.User
import com.example.swimminginstructorlocator.data.repo.AuthRepo
import com.example.swimminginstructorlocator.data.request.LoginRequest
import com.example.swimminginstructorlocator.data.request.RegisterRequest
import com.example.swimminginstructorlocator.data.service.impl.AuthServiceImpl
import com.example.swimminginstructorlocator.listener.OnResultListener
import com.example.swimminginstructorlocator.utils.DataLocalManager

class AuthService(
    private val authRepo: AuthRepo
) : AuthServiceImpl {
    override fun login(
        loginRequest: LoginRequest,
        onResultListener: OnResultListener<User>
    ) {
        authRepo.login(loginRequest, onResultListener)
    }

    override fun register(
        registerRequest: RegisterRequest,
        onResultListener: OnResultListener<User>
    ) {
        authRepo.register(registerRequest, onResultListener)
    }

    override fun logout(onResultListener: OnResultListener<Boolean>) {
        DataLocalManager.removeUser()
        onResultListener.onSuccess(true)
    }

    override fun getCurrentUser(onResultListener: OnResultListener<User>) {
        val user  = DataLocalManager.getUser()
        user?.let { onResultListener.onSuccess(it) }
    }

    companion object {
        private var instance: AuthService? = null

        fun getInstance(authRepo: AuthRepo) = synchronized(this) {
            instance ?: AuthService(authRepo).also {
                instance = it
            }
        }
    }
}