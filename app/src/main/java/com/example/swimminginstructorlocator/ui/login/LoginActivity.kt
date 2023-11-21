package com.example.swimminginstructorlocator.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.swimminginstructorlocator.data.model.User
import com.example.swimminginstructorlocator.data.repo.AuthRepo
import com.example.swimminginstructorlocator.data.request.LoginRequest
import com.example.swimminginstructorlocator.databinding.ActivityLoginBinding
import com.example.swimminginstructorlocator.data.service.AuthService
import com.example.swimminginstructorlocator.listener.OnResultListener

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var authService: AuthService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authService = AuthService(AuthRepo.getInstance())

        binding.btnLogin.setOnClickListener {
            doLogin()
        }
    }

    fun doLogin() {
        val email = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        if (email.isBlank() || password.isBlank()) {
            showToast("Email and password cannot be empty")
        } else {
            val loginRequest = LoginRequest(email, password)
            if (::authService.isInitialized) {
                authService.login(loginRequest, object : OnResultListener<User> {
                    override fun onSuccess(dataResult: User) {
                        showToast("Login success")
                    }

                    override fun onError(exception: Exception?) {
                        showToast("Login failed")
                    }
                })
            } else {
                showToast("AuthService has not been initialized")
            }
        }
    }
    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}