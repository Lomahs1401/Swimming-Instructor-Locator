package com.example.swimminginstructorlocator.utils.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseViewBindingFragment<T : ViewBinding> : Fragment() {

    private var mBinding: T? = null
    protected val binding: T
        get() = mBinding!!

    abstract fun createBindingFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): T

    abstract fun initView()
    abstract fun initData()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = createBindingFragment(inflater, container)

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}
