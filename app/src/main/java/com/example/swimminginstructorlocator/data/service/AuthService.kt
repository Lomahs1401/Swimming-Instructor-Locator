package com.example.swimminginstructorlocator.data.service

import com.example.swimminginstructorlocator.api.AuthApi
import com.example.swimminginstructorlocator.data.model.User
import com.example.swimminginstructorlocator.data.repo.AuthRepo
import com.example.swimminginstructorlocator.data.request.LoginRequest
import com.example.swimminginstructorlocator.data.service.impl.AuthServiceImpl
import com.example.swimminginstructorlocator.listener.OnResultListener


class AuthService(
    private val authRepo: AuthRepo
) : AuthServiceImpl {
    override fun login(
        loginRequest: LoginRequest,
        onResultListener: OnResultListener<User>
    ) {
        authRepo.login(loginRequest, onResultListener)
    }
}