package com.example.swimminginstructorlocator.ui.register

import android.os.Bundle
import com.example.swimminginstructorlocator.R
import com.example.swimminginstructorlocator.databinding.ActivityRegisterBinding
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingActivity

class RegisterActivity : BaseViewBindingActivity<ActivityRegisterBinding>() {
    override fun createBindingActivity(): ActivityRegisterBinding {
        return ActivityRegisterBinding.inflate(layoutInflater)
    }

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }
}