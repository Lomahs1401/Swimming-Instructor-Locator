package com.example.swimminginstructorlocator

import android.util.Log
import androidx.fragment.app.Fragment
import com.example.swimminginstructorlocator.adapter.MainPagerAdapter
import com.example.swimminginstructorlocator.api.InstructorApi
import com.example.swimminginstructorlocator.constant.Constant
import com.example.swimminginstructorlocator.data.model.Instructor
import com.example.swimminginstructorlocator.databinding.ActivityMainBinding
import com.example.swimminginstructorlocator.ui.course.list.CourseListFragment
import com.example.swimminginstructorlocator.ui.dashboard.DashboardFragment
import com.example.swimminginstructorlocator.ui.home.HomeFragment
import com.example.swimminginstructorlocator.ui.instructor.detail.InstructorDetailFragment
import com.example.swimminginstructorlocator.ui.instructor.search.SearchInstructorFragment
import com.example.swimminginstructorlocator.ui.notifications.NotificationsFragment
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : BaseViewBindingActivity<ActivityMainBinding>() {

    private val pagerAdapter: MainPagerAdapter by lazy {
        MainPagerAdapter(this, getFragmentList())
    }

    override fun createBindingActivity(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.viewPager.adapter = pagerAdapter
        binding.viewPager.isUserInputEnabled = false

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> binding.viewPager.currentItem = 0
                R.id.navigation_dashboard -> binding.viewPager.currentItem = 1
                R.id.navigation_notifications -> binding.viewPager.currentItem = 2
                R.id.action_teacher_detail -> binding.viewPager.currentItem = 3
                R.id.action_search_teacher -> binding.viewPager.currentItem = 4
            }

            return@setOnItemSelectedListener true
        }
    }

    override fun initData() {
//        val api = Retrofit.Builder()
//            .baseUrl(Constant.BASE_URL_USER)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(InstructorApi::class.java)
//
//        api.getInstructors().enqueue(object : Callback<MutableList<Instructor>> {
//            override fun onResponse(
//                call: Call<MutableList<Instructor>>,
//                response: Response<MutableList<Instructor>>
//            ) {
//                if (response.isSuccessful) {
//                    response.body()?.let {
//                        for (instructor in it) {
//                            Log.i(Constant.TAG, "onResponse: ${instructor.name}")
//                        }
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<MutableList<Instructor>>, t: Throwable) {
//                Log.e(Constant.TAG, "onFailure: ${t.message}")
//            }
//        })
    }

    private fun getFragmentList(): List<Fragment> {
        return listOf(
            HomeFragment.newInstance(),
            DashboardFragment.newInstance(),
            NotificationsFragment.newInstance(),
            SearchInstructorFragment.newInstance(),
            InstructorDetailFragment.newInstance(),
        )
    }
}