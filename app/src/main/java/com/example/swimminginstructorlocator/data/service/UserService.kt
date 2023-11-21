package com.example.swimminginstructorlocator.data.service

import com.example.swimminginstructorlocator.data.model.User
import com.example.swimminginstructorlocator.data.repo.UserRepo
import com.example.swimminginstructorlocator.data.service.impl.UserServiceImpl
import com.example.swimminginstructorlocator.listener.OnResultListener

class UserService(
    private val userRepo: UserRepo
) : UserServiceImpl {
    override fun login(email: String, password: String, listener: OnResultListener<User>) {
        userRepo.login(email, password, listener)
    }

    override fun register(username: String, email: String, password: String, listener: OnResultListener<User>) {
        userRepo.register(username, email, password, listener)
    }

    companion object {
        private var instance: UserService? = null

        fun getInstance(userRepo: UserRepo) = synchronized(this) {
            instance ?: UserService(userRepo).also {
                instance = it
            }
        }
    }
}