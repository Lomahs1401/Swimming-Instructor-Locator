package com.example.swimminginstructorlocator.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.swimminginstructorlocator.databinding.FragmentNotificationsBinding
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingFragment
import java.lang.Exception

class NotificationsFragment : BaseViewBindingFragment<FragmentNotificationsBinding>(),
    NotificationsContract.View {

    override fun createBindingFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNotificationsBinding {
        return FragmentNotificationsBinding.inflate(inflater, container, false)
    }

    override fun initData() {
//        TODO("Not yet implemented")
    }

    override fun initView() {
//        TODO("Not yet implemented")
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = NotificationsFragment()
    }
}