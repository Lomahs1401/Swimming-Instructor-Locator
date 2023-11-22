package com.example.swimminginstructorlocator.data.service.impl

import com.example.swimminginstructorlocator.data.model.User
import com.example.swimminginstructorlocator.data.request.LoginRequest
import com.example.swimminginstructorlocator.data.request.RegisterRequest
import com.example.swimminginstructorlocator.listener.OnResultListener

interface AuthServiceImpl {
    fun login(loginRequest: LoginRequest, onResultListener: OnResultListener<User>)
    fun register(registerRequest: RegisterRequest, onResultListener: OnResultListener<User>)
    fun getCurrentUser(onResultListener: OnResultListener<User>)
}
