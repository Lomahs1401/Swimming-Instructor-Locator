package com.example.swimminginstructorlocator.data.service.impl

import com.example.swimminginstructorlocator.data.model.User
import com.example.swimminginstructorlocator.listener.OnResultListener

interface UserServiceImpl {
    fun login(email: String, password: String, listener: OnResultListener<User>)
    fun register(username: String, email: String, password: String, listener: OnResultListener<User>)
}