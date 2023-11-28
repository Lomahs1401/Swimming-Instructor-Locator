package com.example.swimminginstructorlocator

import android.content.Intent
import androidx.fragment.app.Fragment
import com.example.swimminginstructorlocator.adapter.MainPagerAdapter
import com.example.swimminginstructorlocator.animation.ZoomOutPageTransformer
import com.example.swimminginstructorlocator.databinding.ActivityMainBinding
import com.example.swimminginstructorlocator.ui.calendar.CalendarFragment
import com.example.swimminginstructorlocator.ui.dashboard.DashboardFragment
import com.example.swimminginstructorlocator.ui.home.HomeFragment
import com.example.swimminginstructorlocator.ui.notifications.NotificationsFragment
import com.example.swimminginstructorlocator.ui.onboarding.OnBoardingActivity
import com.example.swimminginstructorlocator.ui.profile.ProfileFragment
import com.example.swimminginstructorlocator.utils.DataLocalManager
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingActivity

class MainActivity : BaseViewBindingActivity<ActivityMainBinding>() {

    private val pagerAdapter: MainPagerAdapter by lazy {
        MainPagerAdapter(this, getFragmentList())
    }

    override fun createBindingActivity(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        if (!DataLocalManager.getFirstInstalled()) {
            DataLocalManager.setFirstInstalled(true)
            Intent(this, OnBoardingActivity::class.java).also {
                startActivity(it)
            }
        } else {
//            Intent(this, LoginActivity::class.java).also {
//                startActivity(it)
//            }
            binding.viewPager.adapter = pagerAdapter
            binding.viewPager.isUserInputEnabled = false
            binding.viewPager.setPageTransformer(
                ZoomOutPageTransformer(
                    ActivityMainBinding.inflate(
                        layoutInflater
                    )
                )
            )

            binding.bottomNav.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_home -> binding.viewPager.currentItem = 0
                    R.id.navigation_dashboard -> binding.viewPager.currentItem = 1
                    R.id.navigation_notifications -> binding.viewPager.currentItem = 2
                    R.id.navigation_profile -> binding.viewPager.currentItem = 3
                    R.id.navigation_calendar -> binding.viewPager.currentItem = 4
                }

                return@setOnItemSelectedListener true
            }
        }
    }

    override fun initData() {

    }

    private fun getFragmentList(): List<Fragment> {
        return listOf(
            HomeFragment.newInstance(),
            DashboardFragment.newInstance(),
            NotificationsFragment.newInstance(),
            ProfileFragment.newInstance(),
            CalendarFragment.newInstance(),
        )
    }
}