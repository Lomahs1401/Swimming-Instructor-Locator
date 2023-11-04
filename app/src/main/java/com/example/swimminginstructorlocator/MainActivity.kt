package com.example.swimminginstructorlocator

import androidx.fragment.app.Fragment
import com.example.swimminginstructorlocator.adapter.MainPagerAdapter
import com.example.swimminginstructorlocator.databinding.ActivityMainBinding
import com.example.swimminginstructorlocator.ui.dashboard.DashboardFragment
import com.example.swimminginstructorlocator.ui.home.HomeFragment
import com.example.swimminginstructorlocator.ui.instructor.detail.InstructorDetailFragment
import com.example.swimminginstructorlocator.ui.instructor.search.SearchInstructorFragment
import com.example.swimminginstructorlocator.ui.notifications.NotificationsFragment
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingActivity

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