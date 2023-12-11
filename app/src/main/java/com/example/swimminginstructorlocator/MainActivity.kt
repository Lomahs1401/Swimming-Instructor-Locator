package com.example.swimminginstructorlocator

import android.content.Intent
import android.content.res.Configuration
import android.view.View
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.swimminginstructorlocator.adapter.MainPagerAdapter
import com.example.swimminginstructorlocator.databinding.ActivityMainBinding
import com.example.swimminginstructorlocator.ui.appointment.list.AppointmentListFragment
import com.example.swimminginstructorlocator.ui.home.HomeFragment
import com.example.swimminginstructorlocator.ui.login.LoginActivity
import com.example.swimminginstructorlocator.ui.notifications.NotificationsFragment
import com.example.swimminginstructorlocator.ui.onboarding.OnBoardingActivity
import com.example.swimminginstructorlocator.ui.profile.ProfileFragment
import com.example.swimminginstructorlocator.utils.DataLocalManager
import com.example.swimminginstructorlocator.utils.SwimmingInstructorLocatorSharedPreferences
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingActivity
import com.example.swimminginstructorlocator.utils.ext.addFragment
import com.example.swimminginstructorlocator.utils.ext.loadImageWithUrl
import com.example.swimminginstructorlocator.utils.ext.notNull
import java.util.Locale

class MainActivity : BaseViewBindingActivity<ActivityMainBinding>() {

    private val pagerAdapter: MainPagerAdapter by lazy {
        MainPagerAdapter(this, getFragmentList())
    }

    private lateinit var sharedPreferences: SwimmingInstructorLocatorSharedPreferences
    private var isDrawerOpen = false

    override fun createBindingActivity(): ActivityMainBinding {
        sharedPreferences = SwimmingInstructorLocatorSharedPreferences(applicationContext)

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

            val bottomNavMenu = binding.bottomNav.menu
            bottomNavMenu.findItem(R.id.navigation_calendar)?.isVisible = DataLocalManager.isLoggedIn()

            binding.bottomNav.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_home -> binding.viewPager.currentItem = 0
                    R.id.navigation_calendar -> binding.viewPager.currentItem = 1
                    R.id.navigation_notifications -> binding.viewPager.currentItem = 2
                }

                return@setOnItemSelectedListener true
            }
        }
    }

    override fun initData() {
        binding.userImg.setOnClickListener {
            toggleDrawer()
        }

        if (DataLocalManager.isLoggedIn()) {
            val user = DataLocalManager.getUser()
            binding.tvLoginStatus.text = getString(R.string.welcome_back, user?.username)
            user?.avatar.notNull {
                user?.avatar?.let { it1 -> binding.userImg.loadImageWithUrl(it1) }
            }
        } else {
            binding.tvLoginStatus.text = getString(R.string.you_re_not_logged_in_yet)
        }

        updateSidebarContent()

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
                    true
                }

                R.id.login -> {
                    Intent(this, LoginActivity::class.java).also {
                        startActivity(it)
                        finish()
                    }
                    closeDrawer()
                    true
                }

                R.id.logout -> {
                    DataLocalManager.removeUser()
                    DataLocalManager.setIsLoggedIn(false)
                    Intent(this, LoginActivity::class.java).also {
                        startActivity(it)
                        finish()
                    }
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
    }

    private fun updateSidebarContent() {
        val isLoggedIn = DataLocalManager.isLoggedIn()

        // Tìm các TextView trong sidebar_template.xml
        val headerLayout = binding.navigationView.getHeaderView(0)
        val tvNotLoggedIn: TextView = headerLayout.findViewById(R.id.tv_not_logged_in_yet)
        val tvFullName: TextView = headerLayout.findViewById(R.id.name)
        val tvEmail: TextView = headerLayout.findViewById(R.id.email)

        // Kiểm tra xem có tồn tại NavigationView trong view không
        binding.navigationView.let {
            val menu = it.menu
            val menuManageAccount = menu.findItem(R.id.manage_account)
            val menuLogin = menu.findItem(R.id.login)
            val menuLogout = menu.findItem(R.id.logout)

            if (isLoggedIn) {
                menuManageAccount.isVisible = true
                menuLogin.isVisible = false
                menuLogout.isVisible = true

                tvNotLoggedIn.visibility = View.GONE
                tvFullName.visibility = View.VISIBLE
                tvEmail.visibility = View.VISIBLE

                val user = DataLocalManager.getUser()
                tvFullName.text = user?.fullName
                tvEmail.text = user?.email
            } else {
                menuManageAccount.isVisible = false
                menuLogin.isVisible = true
                menuLogout.isVisible = false

                tvNotLoggedIn.visibility = View.VISIBLE
                tvFullName.visibility = View.GONE
                tvEmail.visibility = View.GONE
            }
        }
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
        return if (DataLocalManager.isLoggedIn()) {
            listOf(
                HomeFragment.newInstance(),
                AppointmentListFragment.newInstance(),
                NotificationsFragment.newInstance(),
            )
        } else {
            listOf(
                HomeFragment.newInstance(),
                NotificationsFragment.newInstance(),
            )
        }
    }
}