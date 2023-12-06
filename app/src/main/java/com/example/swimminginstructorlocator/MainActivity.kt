package com.example.swimminginstructorlocator

import android.content.Intent
import android.content.res.Configuration
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.swimminginstructorlocator.adapter.MainPagerAdapter
import com.example.swimminginstructorlocator.databinding.ActivityMainBinding
import com.example.swimminginstructorlocator.ui.calendar.CalendarFragment
import com.example.swimminginstructorlocator.ui.home.HomeFragment
import com.example.swimminginstructorlocator.ui.instructor.detail.InstructorDetailFragment
import com.example.swimminginstructorlocator.ui.login.LoginActivity
import com.example.swimminginstructorlocator.ui.notifications.NotificationsFragment
import com.example.swimminginstructorlocator.ui.onboarding.OnBoardingActivity
import com.example.swimminginstructorlocator.ui.profile.ProfileFragment
import com.example.swimminginstructorlocator.utils.DataLocalManager
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingActivity
import com.example.swimminginstructorlocator.utils.ext.addFragment
import java.util.Locale

class MainActivity : BaseViewBindingActivity<ActivityMainBinding>() {

    private val pagerAdapter: MainPagerAdapter by lazy {
        MainPagerAdapter(this, getFragmentList())
    }

    private var isDrawerOpen = false

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
            binding.viewPager.adapter = pagerAdapter
            binding.viewPager.isUserInputEnabled = false

            binding.bottomNav.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_home -> binding.viewPager.currentItem = 0
                    R.id.navigation_calendar -> binding.viewPager.currentItem = 1
                    R.id.navigation_profile -> binding.viewPager.currentItem = 2
                    R.id.navigation_notifications -> binding.viewPager.currentItem = 3
                }

                return@setOnItemSelectedListener true
            }

            // Xử lý khi chọn mục từ left sidebar
            binding.navigationView.setNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.manage_account -> {
                        val profileFragment = ProfileFragment.newInstance()
                        addFragment(
                            R.id.fragment_home_container,
                            profileFragment,
                            addToBackStack = true
                        )
                        closeDrawer()
                        // Xử lý khi chọn Menu Item 2
                        true
                    }
                    R.id.login -> {
                        Intent(this, LoginActivity::class.java).also {
                            startActivity(it)
                            finish()
                        }
                        closeDrawer()
                        // Xử lý khi chọn Menu Item 2
                        true
                    }
                    R.id.english -> {
                        setLocale("en")
                        closeDrawer()
                        true
                    }
                    R.id.japanese -> {
                        setLocale("jp")
                        closeDrawer()
                        true
                    }
                    R.id.share -> {
                        SweetAlertDialog(this@MainActivity, SweetAlertDialog.NORMAL_TYPE).apply {
                            setTitleText(R.string.update_soon)
                            setCancelable(true)
                            show()
                        }
                        closeDrawer()
                        true
                    }
                    R.id.rating -> {
                        SweetAlertDialog(this@MainActivity, SweetAlertDialog.NORMAL_TYPE).apply {
                            setTitleText(R.string.update_soon)
                            setCancelable(true)
                            show()
                        }
                        closeDrawer()
                        true
                    }
                    else -> false
                }
            }

            binding.userImg.setOnClickListener {
                toggleDrawer()
            }
        }
    }

    override fun initData() {

    }

    private fun toggleDrawer() {
        if (isDrawerOpen) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        isDrawerOpen = !isDrawerOpen
    }

    private fun closeDrawer() {
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        isDrawerOpen = false
    }

    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources = resources
        val configuration = Configuration(resources.configuration)
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)

        recreate()
    }

    private fun getFragmentList(): List<Fragment> {
        return listOf(
            HomeFragment.newInstance(),
            CalendarFragment.newInstance(),
            ProfileFragment.newInstance(),
            NotificationsFragment.newInstance(),
        )
    }
}