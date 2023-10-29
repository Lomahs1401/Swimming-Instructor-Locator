package com.example.swimminginstructorlocator.ui.home

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.example.swimminginstructorlocator.R
import com.example.swimminginstructorlocator.adapter.HomeChildAdapter
import com.example.swimminginstructorlocator.api.InstructorApi
import com.example.swimminginstructorlocator.api.UserApi
import com.example.swimminginstructorlocator.constant.Constant
import com.example.swimminginstructorlocator.data.model.Center
import com.example.swimminginstructorlocator.data.model.Course
import com.example.swimminginstructorlocator.data.model.HomeChild
import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.data.model.User
import com.example.swimminginstructorlocator.data.repo.InstructorRepo
import com.example.swimminginstructorlocator.data.service.remote.InstructorServiceRemote
import com.example.swimminginstructorlocator.databinding.FragmentHomeBinding
import com.example.swimminginstructorlocator.listener.OnInstructorItemClickListener
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.lang.Exception

class HomeFragment : BaseViewBindingFragment<FragmentHomeBinding>(), HomeContract.View, OnInstructorItemClickListener {

    private lateinit var dialog: ProgressDialog
    private lateinit var homePresenter: HomePresenter

    private var listCenters: MutableList<Center> = mutableListOf()
    private var listCourses: MutableList<Course> = mutableListOf()
    private var listInstructors: MutableList<Instructor> = mutableListOf()

    private val homeChildAdapter: HomeChildAdapter by lazy {
        HomeChildAdapter(
            resources = resources,
            presenter = homePresenter,
            instructorItemClickListener = this
        )
    }

    override fun createBindingFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initData() {
        homePresenter = HomePresenter(
            null,
            InstructorServiceRemote.getInstance(InstructorRepo.getInstance())
        )
        homePresenter.setView(this)

        dialog = ProgressDialog(context).apply {
            setTitle(getString(R.string.loading))
            show()
        }

        dialog.dismiss()
    }

    private fun getUsers() {
        UserApi.userApi.getUsers().enqueue(object : Callback<UserApi.ApiResponse> {
            override fun onResponse(
                call: Call<UserApi.ApiResponse>,
                response: Response<UserApi.ApiResponse>
            ) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    val listUsers = apiResponse?.user
                    listUsers?.let {
                        for (user in it) {
                            Log.i(Constant.TAG, "onResponse: ${user.username}")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<UserApi.ApiResponse>, t: Throwable) {
                Log.e(Constant.TAG, "onFailure: ${t.message}")
            }
        })
    }

    override fun initView() {
        homeChildAdapter.setData(getListHomeChild())
        binding.rcvHomeParent.adapter = homeChildAdapter

        getUsers()
    }

    override fun onGetListCenters(listCenters: MutableList<Center>) {
//        TODO("Not yet implemented")
    }

    override fun onGetListCourses(listCourse: MutableList<Course>) {
//        TODO("Not yet implemented")
    }

    override fun onGetListInstructors(listInstructors: MutableList<Instructor>) {
//        TODO("Not yet implemented")
    }

    override fun onViewMoreCenters() {
//        TODO("Not yet implemented")
    }

    override fun onViewMoreCourses() {
//        TODO("Not yet implemented")
    }

    override fun onViewMoreInstructors() {
//        TODO("Not yet implemented")
    }

    override fun onInstructorImageClick(instructor: Instructor) {
//        TODO("Not yet implemented")
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        homeChildAdapter.notifyDataSetChanged()
    }

    private fun getListHomeChild(): MutableList<HomeChild> {
        return mutableListOf(
            HomeChild(HomeChildAdapter.HOME_CENTER_VIEW),
            HomeChild(HomeChildAdapter.HOME_COURSE_VIEW),
            HomeChild(HomeChildAdapter.HOME_INSTRUCTOR_VIEW)
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}