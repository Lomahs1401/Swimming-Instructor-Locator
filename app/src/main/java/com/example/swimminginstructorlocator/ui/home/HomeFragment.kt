package com.example.swimminginstructorlocator.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.data.repo.InstructorRepo
import com.example.swimminginstructorlocator.data.repo.source.remote.InstructorRemoteDataSource
import com.example.swimminginstructorlocator.databinding.FragmentHomeBinding
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingFragment
import java.lang.Exception

class HomeFragment : BaseViewBindingFragment<FragmentHomeBinding>(), HomeContract.View {

    private lateinit var homePresenter: HomePresenter
    private var listInstructors: MutableList<Instructor> = mutableListOf()

    override fun createBindingFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initData() {
        homePresenter = HomePresenter(
            InstructorRepo.getInstanceInstructorRemoteRepo(InstructorRemoteDataSource.getInstance())
        )
        homePresenter.setView(this)
    }

    override fun initView() {
//        TODO("Not yet implemented")
    }

    override fun onGetListInstructor() {
//        TODO("Not yet implemented")
    }

    override fun onSearchInstructor() {
//        TODO("Not yet implemented")
    }

    override fun onViewMoreInstructors() {
//        TODO("Not yet implemented")
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}